//package org.pgscala.util
//package test
//package record
//
//import org.scalatest.{FeatureSpec, GivenWhenThen}
//import org.scalatest.matchers.MustMatchers
//
//class PGIdentSpec extends FeatureSpec
//                  with GivenWhenThen
//                  with MustMatchers {
//
//  feature("Identities can be quoted in PostgreSQL manner") {
//
//    val origStr = "Schema \"Bohema\""
//    val quotStr = "\"Schema \"\"Bohema\"\"\""
//
//    scenario("Ident can be quoted") {
//      PGIdent.quote(origStr) must equal (quotStr)
//    }
//
//    scenario("Boundary conditions must satisfy preset rules") {
//      info("The quote must throw a NullPointerException on null input")
//      intercept[IllegalArgumentException]{
//        PGIdent.quote(null: String)
//      }
//
//      info("""The quote must return "" on empty string input""")
//      PGIdent.quote("") must equal ("\"\"")
//    }
//
//    scenario("Ident quoting mimics PostgreSQL quote_ident") {
//      val dbQuotStr = PGTestDb.qry("SELECT quote_ident(%s);" format PGLiteral.quoteString(origStr)){ rS =>
//        rS.next()
//        rS.getString(1)
//      }
//
//      dbQuotStr must equal(quotStr)
//    }
//
//    scenario("Random generated idents must be able to make a roundabout trip") {
//      val isReadible = false
//
//      val trials = 100
//      val maxLength = 50
//      val emptyRatio = 0.05
//
//      import scala.util.Random
//      val seed = Random.nextInt
//      Random.setSeed(seed)
//
//      given("%d random idents up to %d chars in length" format (trials, maxLength))
//      and("a random seed of [%d]" format seed)
//
//      val values =
//        for (i <- 1 to trials) yield {
//          val len = Random.nextInt(maxLength)
//          val isEmpty = Random.nextFloat() < emptyRatio
//
//          if (isEmpty) {
//            ""
//          }
//          else if (isReadible) {
//            new String(Array.fill(len){Random.nextPrintableChar()})
//          }
//          else {
//            Random.nextString(len)
//          }
//        }
//
//      val queryStub = """
//        SELECT
//          orig_str,
//          quote_ident(orig_str) AS quot_str
//        FROM (VALUES
//          %s
//        ) v(orig_str);
//      """
//
//      val quotes =
//        values.map(v =>
//          v -> PGIdent.quote(v)
//        )
//
//      val query =
//        queryStub.format(
//          quotes.map{ case (origGenStr, quotGenStr) =>
//            "(%s)" format PGLiteral.quoteString(origGenStr)
//          }.mkString(",\n")
//        )
//
//      when("they are quoted and embedded into a query (%d chars)" format query.length)
//      then("the returned idents must equal the original idents")
//
//      val okCount = PGTestDb.qry(query){rS =>
//        quotes.count{ case (origGenStr, quotGenStr) =>
//          rS.next()
//
//          val dbOrigGenStr = rS.getString("orig_str")
//          origGenStr must equal (dbOrigGenStr)
//
//          val dbQuotGenStr = rS.getString("quot_str")
//          quotGenStr must equal (dbQuotGenStr)
//
//          true
//        }
//      }
//
//      info("Passed %d checks!" format okCount)
//      okCount must be (values.size)
//    }
//  }
//}
