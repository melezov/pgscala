package hr.element.pgscala.util
package test

import org.scalatest.{FeatureSpec, GivenWhenThen}
import org.scalatest.matchers.MustMatchers

class PGLiteralSpec extends FeatureSpec
                    with GivenWhenThen
                    with MustMatchers {
  feature("Primitives can be converted into string literals") {

    val origStr = "It's OK!"
    val quotStr  = "'It''s OK!'"

    scenario("String can be quoted") {
      PGLiteral.quoteLiteral(origStr) must equal (quotStr)
    }

    scenario("Strings can be quoted to be directly embedded into queries") {
      val dbOrigStr = PGTestDb.qry("SELECT %s;" format quotStr){ rS =>
        rS.next()
        rS.getString(1)
      }

      dbOrigStr must equal (origStr)
    }

    scenario("Strings quoting mimics PostgreSQL quote_literal") {
      val dbEscStr = PGTestDb.qry("SELECT quote_literal(%s);" format quotStr){ rS =>
        rS.next()
        rS.getString(1)
      }

      dbEscStr must equal (quotStr)
    }

    scenario("Random generated strings must be able to make a roundabout trip") {
      val trials = 1000 * 1000
      val length = 1000

      given("%d random strings %d chars in length")
      when("the said strings are quoted and embedded into a query")
      then("the returning value must equal the original quoted string")

      val rnd = scala.util.Random

      for (i <- 1 until trials) {
        val origGenStr = rnd.nextString(length)
        val escGenStr = PGLiteral.quoteLiteral(origGenStr)
      }
    }
  }
}