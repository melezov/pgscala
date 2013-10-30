package org.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers

class StringTest extends FeatureSpec with GivenWhenThen with Matchers {
  feature("About to test an String converter"){
    info("I want to test if PGNullableStringConverter works correctly")

    scenario("String to String Nr. 1."){
      val t = """Ovo je test za String"""
      Given(""" a starting String value "%s"""" format t)
      When ("that value is converted to String")
      val res = PGNullableStringConverter stringToString t
      Then ("""It should return a String value "%s"""" format res)
      res should equal(t)
    }
  }
}
