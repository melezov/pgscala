package org.pgscala.util
package test
package ident

import org.junit.runner.RunWith
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PGIdentSpec
    extends FeatureSpec with GivenWhenThen with ShouldMatchers {

  feature("Identities can be quoted in PostgreSQL manner") {

    val origStr = "Schema \"Bohema\""
    val quotStr = "\"Schema \"\"Bohema\"\"\""

    scenario("Ident can be quoted") {
      PGIdent.quote(origStr) should equal (quotStr)
    }

    scenario("Boundary conditions must satisfy preset rules") {
      info("The quote must throw a NullPointerException on null input")
      intercept[IllegalArgumentException]{
        PGIdent.quote(null: String)
      }

      info("""The quote must return "" on empty string input""")
      PGIdent.quote("") should equal ("\"\"")
    }

    scenario("Ident quoting mimics PostgreSQL quote_ident") {
      val dbQuotStr = PGTestDb.qry("SELECT quote_ident(%s);" format PGLiteral.quote(origStr)){ rS =>
        rS.next()
        rS.getString(1)
      }

      dbQuotStr should equal(quotStr)
    }

    scenario("Random generated idents should be able to make a roundabout trip") {
      val isReadible = false

      val trials = 100
      val maxLength = 50
      val emptyRatio = 0.05

      import scala.util.Random
      val seed = Random.nextLong
      Random.setSeed(seed)

      Given("%d random idents up to %d chars in length" format (trials, maxLength))
      And("a random seed of [%d]" format seed)

      val values =
        for (i <- 1 to trials) yield {
          val len = Random.nextInt(maxLength)
          val isEmpty = Random.nextFloat() < emptyRatio

          if (isEmpty) {
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
          quote_ident(orig_str) AS quot_str
        FROM (VALUES
          %s
        ) v(orig_str);
      """

      val quotes =
        values.map(v =>
          v -> PGIdent.quote(v)
        )

      val query =
        queryStub.format(
          quotes.map{ case (origGenStr, quotGenStr) =>
            "(%s)" format PGLiteral.quote(origGenStr)
          }.mkString(",\n")
        )

      When("they are quoted and embedded into a query (%d chars)" format query.length)
      Then("the returned idents should equal the original idents")

      val okCount = PGTestDb.qry(query){rS =>
        quotes.count{ case (origGenStr, quotGenStr) =>
          rS.next()

          val dbOrigGenStr = rS.getString("orig_str")
          origGenStr should equal (dbOrigGenStr)

          val dbQuotGenStr = rS.getString("quot_str")
          quotGenStr should equal (dbQuotGenStr)

          true
        }
      }

      info("Passed %d checks!" format okCount)
      okCount should be (values.size)
    }
  }
}
