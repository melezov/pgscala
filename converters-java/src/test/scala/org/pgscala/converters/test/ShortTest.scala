package org.pgscala.converters
package test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class ShortTest extends FeatureSpec with GivenWhenThen with Matchers {
  feature("About to test an Short converter"){
    info("I want to test if PGNullableShortConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the Short boundary cases")

    scenario("Short to String Nr. 1"){
      Given ("a starting Short value of Short.MaxValue")
      val n = Short.MaxValue
      When ("that value is converted to String")
      val res = PGNullableShortConverter shortToString n
      Then ("""It should return a String value "%d"""" format n)
      res should equal(n.toString)
     }
    scenario("Short to String Nr. 2"){
      Given ("a starting Short value of Short.MinValue")
      val n = Short.MinValue
      When ("that value is converted to String")
      val res = PGNullableShortConverter shortToString n
      Then ("""It should return a String value "%d"""" format n)
      res should equal(n.toString)
    }
    scenario("Short to String Nr. 3"){
      Given ("a starting Short value of 0")
      val n: Short = 0
      When ("that value is converted to String")
      val res = PGNullableShortConverter shortToString n
      Then ("""It should return a String value "%d"""" format n)
      res should equal(n.toString)
    }

    scenario("String to Short Nr. 1"){
      info("test for select 32767::smallint;")
      Given ("a starting String value of Short.MaxValue")
      val n = "32767"
      When ("that value is converted to Short")
      val res = PGNullableShortConverter stringToShort n
      Then ("It should return an Short value %s" format n)
      res.toString should equal(n)
    }
    scenario("String to Short Nr. 2"){
      info("test for select -32767::smallint;")
      Given ("a starting String value of Short.MinValue")
      val n = "-32767"
      When ("that value is converted to Short")
      val res = PGNullableShortConverter stringToShort n
      Then ("It should return an Short value %s" format n)
      res.toString should equal(n)
    }
    scenario("String to Short Nr. 3"){
      info("test for select 0::smallint;")
      Given ("""a starting String value of "0"""")
      val n = "0"
      When ("that value is converted to Short")
      val res = PGNullableShortConverter stringToShort n
      Then ("It should return an Short value %s" format n)
      res.toString should equal(n)
    }
  }
}
