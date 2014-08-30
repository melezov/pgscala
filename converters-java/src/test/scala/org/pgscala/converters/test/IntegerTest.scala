package org.pgscala.converters
package test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers

@RunWith(classOf[JUnitRunner])
class IntegerTest extends FeatureSpec with GivenWhenThen with Matchers {
  feature("About to test an Integer converter"){
    info("I want to test if PGNullableIntegerConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the Integer boundary cases")

    scenario("Integer to String Nr. 1"){
      Given ("a starting Integer value of Int.MaxValue")
      val n = Int.MaxValue
      When ("that value is converted to String")
      val res = PGNullableIntegerConverter integerToString n
      Then ("""It should return a String value "%d"""" format Int.MaxValue)
      res should equal(Int.MaxValue.toString)
    }

    scenario("Integer to String Nr. 2"){
      Given ("a starting Integer value of Int.MinValue")
      val n = Int.MinValue
      When ("that value is converted to String")
      val res = PGNullableIntegerConverter integerToString n
      Then ("""It should return a String value "%d"""" format Int.MinValue)
      res should equal(Int.MinValue.toString)
    }

    scenario("Integer to String Nr. 3"){
      Given ("a starting Integer value of 0")
      val n = 0
      When ("that value is converted to String")
      val res = PGNullableIntegerConverter integerToString n
      Then ("""It should return a String value "0"""")
      res should equal(n.toString)
    }
    scenario("String to Integer Nr. 1"){
      Given ("a starting String value of Int.MaxValue")
      val n = Int.MaxValue.toString
      When ("that value is converted to Integer")
      val res = PGNullableIntegerConverter stringToInteger n
      Then ("""It should return an Integer value %d""" format Int.MaxValue)
      res.toString should equal(n)
    }

    scenario("String to Integer Nr. 2"){
      Given ("a starting String value of Int.MinValue")
      val n = Int.MinValue.toString
      When ("that value is converted to Integer")
      val res = PGNullableIntegerConverter stringToInteger n
      Then ("""It should return an Integer value %d""" format Int.MinValue)
      res.toString should equal(n)
    }

    scenario("String to Integer Nr. 3"){
      Given ("""a starting String value "0""")
      val n = "0"
      When ("that value is converted to Integer")
      val res = PGNullableIntegerConverter stringToInteger n
      Then ("It should return an Integer value 0")
      res.toString should equal(n)
    }
  }
}
