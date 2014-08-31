package org.pgscala.converters
package test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class FloatTest  extends FeatureSpec with GivenWhenThen with ShouldMatchers {
  feature("About to test a Float converter"){
    info("I want to test if PGNullableFloatConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the Float special cases")

    scenario("Float to String Nr. 1"){
      //select 3.4028235E38::float8;
      val f = Float.MaxValue
      Given ("a starting Float value of %s" format f)
      When ("that value is converted to String")
      val res = PGNullableFloatConverter floatToString f
      Then ("""It should return a String value "%s"""" format res)
      res should equal (f.toString)
    }

    scenario("Float to String Nr. 2"){
      //select -3.4028235E38::float8;
      val f = Float.MinValue
      Given ("a starting Float value of %s" format f)
      When ("that value is converted to String")
      val res = PGNullableFloatConverter floatToString f
      Then ("""It should return a String value "%s"""" format res)
      res should equal (f.toString)
    }

    scenario("Float to String Nr. 3"){
      //select 'NaN'::float8;
      val f = Float.NaN
      Given ("a starting value of %s" format f)
      When ("that value is converted to String")
      val res = PGNullableFloatConverter floatToString f
      Then ("""It should return a String value "%s"""" format res)
      res should equal (f.toString)
    }

    scenario("Float to String Nr. 4"){
      //select 'Infinity'::float8;
      val f = Float.PositiveInfinity
      Given ("a starting value of %s" format f)
      When ("that value is converted to String")
      val res = PGNullableFloatConverter floatToString f
      Then ("""It should return a String value "%s"""" format res)
      res should equal (f.toString)
    }

    scenario("Float to String Nr. 5"){
      //select '-Infinity'::float8;
      val f = Float.NegativeInfinity
      Given ("a starting value of %s" format f)
      When ("that value is converted to String")
      val res = PGNullableFloatConverter floatToString f
      Then ("""It should return a String value "%s"""" format res)
      res should equal (f.toString)
    }

    scenario("String to Float Nr. 1"){
      val s = "3.4028235E38"
      Given ("""a starting String value of "%s""" format s)
      When ("that value is converted to Float")
      val res = PGNullableFloatConverter stringToFloat s
      Then ("It should return a Float value %s" format res)
      res.toString should equal (s)
    }

    scenario("String to Float Nr. 2"){
      val s = "-3.4028235E38"
      Given ("""a starting String value of "%s""" format s)
      When ("that value is converted to Float")
      val res = PGNullableFloatConverter stringToFloat s
      Then ("It should return a Float value %s" format res)
      res.toString should equal (s)
    }

    scenario("String to Float Nr. 3"){
      val s = "NaN"
      Given ("""a starting String value of "%s""" format s)
      When ("that value is converted to Float")
      val res = PGNullableFloatConverter stringToFloat s
      Then ("It should return a Float value %s" format res)
      res.isNaN should equal (Float.NaN.isNaN)
    }

    scenario("String to Float Nr. 4"){
      val s = "Infinity"
      Given ("""a starting String value of "%s""" format s)
      When ("that value is converted to Float")
      val res = PGNullableFloatConverter stringToFloat s
      Then ("It should return a Float value %s" format res)
      res.toString should equal (s)
    }

    scenario("String to Float Nr. 5"){
      val s = "-Infinity"
      Given ("""a starting String value of "%s""" format s)
      When ("that value is converted to Float")
      val res = PGNullableFloatConverter stringToFloat s
      Then ("It should return a Float value %s" format res)
      res.toString should equal (s)
    }
  }
}
