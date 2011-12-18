package hr.element.pgscala.util
package test
package literal

import org.scalatest.{FeatureSpec, GivenWhenThen}
import org.scalatest.matchers.MustMatchers

class PGLiteralBooleanSpec extends FeatureSpec
                    with GivenWhenThen
                    with MustMatchers {
  feature("Booleans can be converted into string literals") {

    val qT = "'t'"
    val qF = "'f'"

    scenario("Boolean conversion") {
      PGLiteral.quoteBoolean(true) must equal (qT)
      PGLiteral.quoteBoolean(false) must equal (qF)
    }

    scenario("Booleans can be quoted to be directly embedded into queries") {
      val dbTrue = PGTestDb.qry("SELECT %s, %s;" format(qT, qF)){ rS =>
        rS.next()
        rS.getBoolean(1) must be (true)
        rS.getBoolean(2) must be (false)
      }
    }
  }
}
