package org.pgscala.converters
package test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers

@RunWith(classOf[JUnitRunner])
class PointTest extends FeatureSpec with GivenWhenThen with Matchers {
  feature("About to test an Point converter") {
    info("I want to test if PGNullablePointConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the Point boundary cases")

    scenario("java.awt.Point to String Nr. 1") {
      Given("a starting Point(Int.Min, Int.MaxValue)")
      val p = new java.awt.Point(Int.MaxValue, Int.MinValue);
      val res = PGNullablePointConverter pointToString p
      When(s"that value is converted to String $res")
      val expectedResult = s"(${Int.MaxValue},${Int.MinValue})"
      Then(s"It should return a String value $expectedResult")
      res should equal(expectedResult)
    }


    scenario("Integer Point to String Nr. 2") {
      Given("a starting Point(0,0)")
      val p = new java.awt.Point(0, 0);
      val res = PGNullablePointConverter pointToString p
      When(s"that value is converted to String $res")
      val expectedResult = "(0,0)"
      Then(s"It should return a String value $expectedResult")
      res should equal(expectedResult)
    }

    scenario("Integer Point to String Nr. 3") {
      Given("a starting Point of random integers...")
      val x = scala.util.Random.nextInt
      val y = scala.util.Random.nextInt
      val p = new java.awt.Point(x, y);
      val res = PGNullablePointConverter pointToString p
      When(s"that value is converted to String: $res")
      val expectedResult = s"(${x},${y})"
      Then(s"It should return a String value $expectedResult")
      res should equal(expectedResult)
    }

    scenario("String to Point Nr. 1") {
      val given = "(2147483647,-2147483648)"
      Given(s"a starting String: $given")
      val res = PGNullablePointConverter stringToPoint given
      When(s"that value is converted to String $res")
      val expectedResult = new java.awt.Point(2147483647, -2147483648);
      Then(s"It should return a Point $expectedResult")
      res should equal(expectedResult)
    }

    scenario("String to Point Nr. 2") {
      val given = "(nan,nan)"
      Given(s"a starting String: $given")
      val res = PGNullablePointConverter stringToPoint(given)
      When(s"that value is converted to String $res")
      val expectedResult = null;
      Then(s"It should return a Point value $expectedResult")
      res should be(null)
    }

    scenario("String to Point Nr. 3") {
      val given = "(0,0)"
      Given(s"a starting String: $given")
      val res = PGNullablePointConverter stringToPoint given
      When(s"that value is converted to String $res")
      val expectedResult = new java.awt.Point(0, 0)
      Then(s"It should return a String value $expectedResult")
      res should equal(expectedResult)
    }

    scenario("String to Point Nr. 4") {
      val given = "(inf,-inf)"
      Given(s"a starting String: $given")
      val res = PGNullablePointConverter stringToPoint(given)
      When(s"that value is converted to String $res")
      val expectedResult = null;
      Then(s"It should return a Point value $expectedResult")
      res should equal(expectedResult)
    }
  }
}
