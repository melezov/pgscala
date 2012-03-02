package org.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers

class StringTest extends FeatureSpec with GivenWhenThen with ShouldMatchers{
  feature("About to test an String converter"){
    info("I want to test if PGNullableStringConverter works correctly")

    scenario("String to String Nr. 1."){
      val t = """Ovo je test za String"""
      given(""" a starting String value "%s"""" format t)
      when ("that value is converted to String")
      val res = PGNullableStringConverter stringToString t
      then ("""It should return a String value "%s"""" format res)
      res should equal(t)
    }
  }
}
