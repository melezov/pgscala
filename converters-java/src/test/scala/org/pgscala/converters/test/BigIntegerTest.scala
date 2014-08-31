package org.pgscala.converters
package test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers

import java.math.BigInteger

@RunWith(classOf[JUnitRunner])
class BigIntegerTest extends FeatureSpec with GivenWhenThen with ShouldMatchers {
  feature("About to test a BigInteger converter"){
    info("I want to test if PGNullableBigIntegerConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the BigInteger boundary cases")

    scenario("BigInteger to String Nr. 1"){
      Given ("a starting BigInteger value of 0")
      val n = BigInteger.ZERO
      When ("that value is converted to String")
      val res = PGNullableBigIntegerConverter bigIntegerToString n
      Then ("""It should return a String value "0"""")
      res should equal(BigInteger.ZERO.toString)
    }

    scenario("BigInteger to String Nr. 2"){
      val n = BigInteger.valueOf(Long.MaxValue + 1)
      Given ("a starting BigInteger value of %d" format n)
      When ("that value is converted to String")
      val res = PGNullableBigIntegerConverter bigIntegerToString n
      Then ("""It should return a String value "%d"""" format n)
      res should equal(n.toString)
    }

    scenario("BigInteger to String Nr. 3"){
      val n = BigInteger.valueOf(Long.MinValue - 1)
      Given ("a starting BigInteger value of %d" format n)
      When ("that value is converted to String")
      val res = PGNullableBigIntegerConverter bigIntegerToString n
      Then ("""It should return a String value "%d"""" format n)
      res should equal(n.toString)
    }

    scenario("String to BigInteger Nr. 1"){
      Given ("""a starting String value of "0"""")
      val n = BigInteger.ZERO.toString
      When ("that value is converted to BigInteger")
      val res = PGNullableBigIntegerConverter stringToBigInteger n
      Then ("It should return a BigInteger value 0")
      res should equal(BigInteger.ZERO)
    }

    scenario("String to BigInteger Nr. 2"){
      val n = BigInteger.valueOf(Long.MaxValue + 1).toString
      Given ("""a starting String value of "%s"""" format n)
      When ("that value is converted to BigInteger")
      val res = PGNullableBigIntegerConverter stringToBigInteger n
      Then ("It should return a BigInteger value %s" format n)
      res should equal(BigInteger.valueOf(Long.MaxValue + 1))
    }

    scenario("String to BigInteger Nr. 3"){
      val n = BigInteger.valueOf(Long.MinValue - 1).toString
      Given ("""a starting String value of "%s""" format n)
      When ("that value is converted to BigInteger")
      val res = PGNullableBigIntegerConverter stringToBigInteger n
      Then ("It should return a BigInteger value %s" format n)
      res should equal(BigInteger.valueOf(Long.MinValue - 1))
    }
  }
}
