package org.pgscala.util
package test
package literal

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class PGLiteralBooleanSpec
    extends FeatureSpec with GivenWhenThen with ShouldMatchers {

  feature("Booleans can be converted into string literals") {

    val qT = "'t'"
    val qF = "'f'"

    scenario("Boolean conversion") {
      PGLiteral.quote(true.toString) should equal ("'true'")
      PGLiteral.quote(false.toString) should equal ("'false'")
    }

    scenario("Booleans can be quoted to be directly embedded into queries") {
      PGTestDb.qry("SELECT %s, %s;" format(qT, qF)){ rS =>
        rS.next()
        rS.getBoolean(1) should be (true)
        rS.getBoolean(2) should be (false)
      }
    }
  }
}
