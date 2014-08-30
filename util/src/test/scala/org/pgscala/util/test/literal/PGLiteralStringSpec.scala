package org.pgscala.util
package test
package literal

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers

@RunWith(classOf[JUnitRunner])
class PGLiteralStringSpec
    extends FeatureSpec with GivenWhenThen with Matchers {

  feature("Strings can be converted into string literals") {

    val origStr = """It's OK -> \ Don"t {worry} be (happy)! /"""
    val quotStr  = """'It''s OK -> \ Don"t {worry} be (happy)! /'"""

    scenario("String can be quoted") {
      PGLiteral.quote(origStr) should equal (quotStr)
    }

    scenario("String can be unquoted") {
      PGLiteral.unquote(quotStr) should equal (origStr)
    }

    scenario("Boundary conditions must satisfy preset rules") {
      info("The quote must return NULL on null input")
      PGLiteral.quote(null: String) should equal ("NULL")

      info("The quote must return '' on empty string input")
      PGLiteral.quote("") should equal ("''")
    }

    scenario("Strings can be quoted to be directly embedded into queries") {
      val dbOrigStr = PGTestDb.qry("SELECT %s;" format quotStr){ rS =>
        rS.next()
        rS.getString(1)
      }

      dbOrigStr should equal (origStr)
    }

    scenario("Strings quoting mimics PostgreSQL quote_literal") {
      val dbQuotStr = PGTestDb.qry("SELECT quote_literal(%s);" format quotStr){ rS =>
        rS.next()
        rS.getString(1)
      }

      Given("the client quoted string: " + quotStr)
      And("the server quoted string: " + dbQuotStr)

      Then("the server must consider them equal via:")
      val response = "SELECT %s = %s;" format (quotStr, dbQuotStr)
      info(response)

      val pgApproves = PGTestDb.qry(response){ rS =>
        rS.next()
        rS.getBoolean(1)
      }

      pgApproves should be (true)
    }

    scenario("Random generated strings should be able to make a roundabout trip") {
      val isReadible = false

      val trials = 100
      val maxLength = 100000
      val nullRatio = 0.05
      val emptyRatio = 0.05

      import scala.util.Random
      val seed = Random.nextLong
      Random.setSeed(seed)

      Given("%d random strings up to %d chars in length" format (trials, maxLength))
      And("a random seed of [%d]" format seed)

      val values =
        for (i <- 1 to trials) yield {
          val len = Random.nextInt(maxLength)
          val isNull = Random.nextFloat() < nullRatio
          val isEmpty = Random.nextFloat() < emptyRatio

          if (isNull) {
            null
          }
          else if (isEmpty) {
            ""
          }
          else if (isReadible) {
            new String(Array.fill(len){Random.nextPrintableChar()})
          }
          else {
            Random.nextString(len)
          }
        }

      val queryStub = """
        SELECT
          orig_str,
          quote_literal(orig_str) AS quot_str
        FROM (VALUES
          %s
        ) v(orig_str);
      """

      val quotes =
        values.map(v =>
          v -> PGLiteral.quote(v)
        )

      val query =
        queryStub.format(
          quotes.map{ case (origGenStr, quotGenStr) =>
            "(%s)" format quotGenStr
          }.mkString(",\n")
        )

      When("they are quoted and embedded into a query (%d chars)" format query.length)
      Then("the returned strings should equal the original strings")

      val dbQuoteValues = PGTestDb.qry(query){rS =>
        values.map{origGenStr =>
          rS.next()

          val dbOrigGenStr = rS.getString("orig_str")
          origGenStr should equal (dbOrigGenStr)

          val dbQuotGenStr = rS.getString("quot_str")
          dbQuotGenStr
        }
      }

      val responseQueryStub = """
        SELECT
          pg_approves
        FROM (VALUES
          %s
        ) v(pg_approves);
      """

      And("PostgreSQL quote_literal must evaluate to the client quoted strings")

      val responseQuery =
        responseQueryStub.format(
          quotes.zip(dbQuoteValues).map{ _ match {
              case ((null, quotGenStr), dbQuotGenStr) =>
                "(%s IS %s)" format(quotGenStr, dbQuotGenStr)
              case ((origGenStr, quotGenStr), dbQuotGenStr)  =>
               "(%s = %s)" format(quotGenStr, dbQuotGenStr)
            }
          }.mkString(",\n")
        )

      val okCount = PGTestDb.qry(responseQuery){rS =>
        dbQuoteValues.count{origGenStr =>
          rS.next()
          rS.getBoolean("pg_approves") should be (true)
          true
        }
      }

      info("Passed %d checks!" format okCount)
      okCount should be (values.size)
    }
  }
}
