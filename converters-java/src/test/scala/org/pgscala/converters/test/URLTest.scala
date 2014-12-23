package org.pgscala.converters
package test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers

import java.net.URL
import scala.util.Random

@RunWith(classOf[JUnitRunner])
class URLTest extends FeatureSpec with GivenWhenThen with Matchers {
  feature("About to test an URL converter") {
    info("I want to test if PGNullableURLConverter works correctly, both in 2 way conversion")

    scenario("URL to String Nr. 1.") {
      val t = new URL("file:///documents/pictures")
      Given(" a starting random URL value %s" format t)
      When("that value is converted to String")
      val res = PGNullableURLConverter urlToString t
      Then("""It should return a String value "%s"""" format res)
      res should equal(t.toString)
    }

    scenario("String to URL Nr. 1.") {
      val t = "https://192.168.100.200/test?q=x"
      Given(""" a starting String value "%s""" format t)
      When("that value is converted to URL")
      val res = PGNullableURLConverter stringToURL t
      Then("It should return a URL value %s" format res)
      res.toString should equal(t)
    }
  }
}
