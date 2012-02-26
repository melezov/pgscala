package hr.element.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers

class IntegerTest extends FeatureSpec with GivenWhenThen with ShouldMatchers{
  feature("About to test an Integer converter"){
    info("I want to test if PGNullableIntegerConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the Integer boundary cases")

    scenario("Integer to String Nr. 1"){
        given ("a starting Integer value of Int.MaxValue")
        val n = Int.MaxValue
        when ("that value is converted to String")
        val res = PGNullableIntegerConverter integerToString n
        then ("""It should return a String value "%d"""" format Int.MaxValue)
        res should equal(Int.MaxValue.toString)
      }

    scenario("Integer to String Nr. 2"){
        given ("a starting Integer value of Int.MinValue")
        val n = Int.MinValue
        when ("that value is converted to String")
        val res = PGNullableIntegerConverter integerToString n
        then ("""It should return a String value "%d"""" format Int.MinValue)
        res should equal(Int.MinValue.toString)
      }

     scenario("Integer to String Nr. 3"){
        given ("a starting Integer value of 0")
        val n = 0
        when ("that value is converted to String")
        val res = PGNullableIntegerConverter integerToString n
        then ("""It should return a String value "0"""")
        res should equal("0")
      }
     scenario("String to Integer Nr. 1"){
        given ("a starting String value of Int.MaxValue")
        val n = Int.MaxValue.toString
        when ("that value is converted to Integer")
        val res = PGNullableIntegerConverter stringToInteger n
        then ("""It should return an Integer value %d""" format Int.MaxValue)
        res should equal(Int.MaxValue)
      }

    scenario("String to Integer Nr. 2"){
        given ("a starting String value of Int.MinValue")
        val n = Int.MinValue.toString
        when ("that value is converted to Integer")
        val res = PGNullableIntegerConverter stringToInteger n
        then ("""It should return an Integer value %d""" format Int.MinValue)
        res should equal(Int.MinValue)
      }

     scenario("String to Integer Nr. 3"){
        given ("""a starting String value "0""")
        val n = "0"
        when ("that value is converted to Integer")
        val res = PGNullableIntegerConverter stringToInteger n
        then ("It should return an Integer value 0")
        res should equal(0)
      }

    }
}
