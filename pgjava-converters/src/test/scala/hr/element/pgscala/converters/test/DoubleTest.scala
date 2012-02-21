package hr.element.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers

class DoubleTest extends FeatureSpec with GivenWhenThen with ShouldMatchers{
  feature("About to test a Double converter"){
    info("I want to test if PGNullableDoubleConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the Double boundary cases")

    scenario("Double to String Nr. 1"){
      given ("a starting Double value of positive infinity")
      val d = Double.PositiveInfinity
      when ("that value is converted to String")
      val res = PGNullableDoubleConverter doubleToString d
      then ("""it should return a String value "%s"""" format d.toString)
      res should equal (d.toString)
    }

    scenario("Double to String Nr. 2"){
      given ("a starting Double value of negative infinity")
      val d = Double.NegativeInfinity
      when ("that value is converted to String")
      val res = PGNullableDoubleConverter doubleToString d
      then ("""it should return a String value "%s"""" format d.toString)
      res should equal (d.toString)
    }

    scenario("Double to String Nr. 3"){
      val d = 0d
      given ("a starting Double value of %s" format d toString)
      when ("that value is converted to String")
      val res = PGNullableDoubleConverter doubleToString d
      then ("""it should return a String value "%s"""" format d.toString)
      res should equal (d.toString)
    }

    scenario("Double to String Nr. 4"){
      val d = Double.NaN
      given ("a starting value of NaN")
      when ("that value is converted to String")
      val res = PGNullableDoubleConverter doubleToString d
      then ("""it should return a String value "%s"""" format d.toString)
      res should equal (d.toString)
    }

    scenario("String to Double Nr. 1"){
      given ("""a starting String value of "Infinity"""")
      val s =  Double.PositiveInfinity.toString
      when ("that value is converted to Double")
      val res = PGNullableDoubleConverter stringToDouble s
      then ("it should return a Double value of %s" format s)
      res should equal (Double.PositiveInfinity)
    }

    scenario("String to Double Nr. 2"){
      given ("""a starting String value of "-Infinity"""")
      val s =  Double.NegativeInfinity.toString
      when ("that value is converted to Double")
      val res = PGNullableDoubleConverter stringToDouble s
      then ("it should return a Double value of %s" format s)
      res should equal (Double.NegativeInfinity)
    }

    scenario("String to Double Nr. 3"){
      given ("""a starting String value of "NaN"""")
      val s =  "NaN"
      when ("that value is converted to Double")
      val res = PGNullableDoubleConverter stringToDouble s
      then ("it should return a Double value of %s" format s)
      res should equal (Double.NaN)
    }

    scenario("String to Double Nr. 4"){
      given ("""a starting String value of "0"""")
      val s =  "0"
      when ("that value is converted to Double")
      val res = PGNullableDoubleConverter stringToDouble s
      then ("it should return a Double value of %s" format s)
      res should equal (0d)
    }





  }
}