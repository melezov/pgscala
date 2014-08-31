package org.pgscala.converters
package test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class DoubleTest extends FeatureSpec with GivenWhenThen with ShouldMatchers {
  feature("About to test a Double converter"){
    info("I want to test if PGNullableDoubleConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the Double boundary cases")

    scenario("Double to String Nr. 1"){
      Given ("a starting Double value of positive infinity")
      val d = Double.PositiveInfinity
      When ("that value is converted to String")
      val res = PGNullableDoubleConverter doubleToString d
      Then ("""it should return a String value "%s"""" format d.toString)
      res should equal (d.toString)
    }

    scenario("Double to String Nr. 2"){
      Given ("a starting Double value of negative infinity")
      val d = Double.NegativeInfinity
      When ("that value is converted to String")
      val res = PGNullableDoubleConverter doubleToString d
      Then ("""it should return a String value "%s"""" format d.toString)
      res should equal (d.toString)
    }

    scenario("Double to String Nr. 3"){
      val d = 0d
      Given ("a starting Double value of %s" format d toString)
      When ("that value is converted to String")
      val res = PGNullableDoubleConverter doubleToString d
      Then ("""it should return a String value "%s"""" format d.toString)
      res should equal (d.toString)
    }

    scenario("Double to String Nr. 4"){
      val d = Double.NaN
      Given ("a starting value of NaN")
      When ("that value is converted to String")
      val res = PGNullableDoubleConverter doubleToString d
      Then ("""it should return a String value "%s"""" format d.toString)
      res should equal (d.toString)
    }

    scenario("String to Double Nr. 1"){
      Given ("""a starting String value of "Infinity"""")
      val s =  Double.PositiveInfinity.toString
      When ("that value is converted to Double")
      val res = PGNullableDoubleConverter stringToDouble s
      Then ("it should return a Double value of %s" format s)
      res.toString should equal (s)
    }

    scenario("String to Double Nr. 2"){
      Given ("""a starting String value of "-Infinity"""")
      val s =  Double.NegativeInfinity.toString
      When ("that value is converted to Double")
      val res = PGNullableDoubleConverter stringToDouble s
      Then ("it should return a Double value of %s" format s)
      res.toString should equal (s)
    }

    scenario("String to Double Nr. 3"){
      Given ("""a starting String value of "NaN"""")
      val s =  "NaN"
      When ("that value is converted to Double")
      val res = PGNullableDoubleConverter stringToDouble s
      Then ("it should return a Double value of %s" format s)
      res.isNaN() should equal (Double.NaN.isNaN())
    }

    scenario("String to Double Nr. 4"){
      Given ("""a starting String value of "0"""")
      val s =  "0"
      When ("that value is converted to Double")
      val res = PGNullableDoubleConverter stringToDouble s
      Then ("it should return a Double value of %s" format s)
      res should equal (0d)
    }
  }
}
