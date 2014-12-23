package org.pgscala.util
package test
package literal

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers

@RunWith(classOf[JUnitRunner])
class PGEscapeLikeSpec
    extends FeatureSpec with GivenWhenThen with Matchers {

  val escapeFunc = (_: String)
    .replace("\\", "\\\\")
    .replace("_", "\\_")
    .replace("%", "\\%")

  feature("Like strings can be escaped") {

    scenario("Random string escaping") {
      for (i <- 0 to 10000) {
        val like = Array.fill(10){scala.util.Random.nextPrintableChar}.mkString
        PGLiteral.escapeLike(like) shouldBe escapeFunc(like)
      }
    }
  }
}
