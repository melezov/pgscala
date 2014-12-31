package org.pgscala.converters
package test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers

@RunWith(classOf[JUnitRunner])
class LocationTest extends FeatureSpec with GivenWhenThen with Matchers {
  feature("About to test an Location converter") {
    info("I want to test if PGNullableLocationConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the Point boundary cases")

    scenario("Double Point to String Nr. 1") {
      Given("a starting Point(Double.Min, Double.MaxValue)")
      val p = new java.awt.geom.Point2D.Double(Double.MaxValue, Double.MinValue);
      val res = PGNullableLocationConverter locationToString p
      When(s"that value is converted to String $res")
      val expectedResult = s"(${Double.MaxValue.toDouble},${Double.MinValue.toDouble})"
      Then(s"It should return a String value $expectedResult")
      res should equal(expectedResult)
    }

    scenario("Double Point to String Nr. 2") {
      Given("a starting Point(Double.NaN, Double.NaN)")
      val p = new java.awt.geom.Point2D.Double(Double.NaN, Double.NaN);
      When("that value is converted to String")
      val res = PGNullableLocationConverter locationToString p
      val expectedResult = "(nan,nan)"
      Then(s"It should return a String value $expectedResult")
      res should equal(expectedResult)
    }

    scenario("Double Point to String Nr. 3") {
      Given("a starting Point(0,0)")
      val p = new java.awt.geom.Point2D.Double(0, 0);
      val res = PGNullableLocationConverter locationToString p
      When(s"that value is converted to String $res")
      val expectedResult = "(0.0,0.0)"
      Then(s"It should return a String value $expectedResult")
      res should equal(expectedResult)
    }

    scenario("Double Point to String Nr. 4") {
      Given("a starting Point(Double.PositiveInfinity, Double.NegativeInfinity)")
      val p = new java.awt.geom.Point2D.Double(Double.PositiveInfinity, Double.NegativeInfinity);
      When("that value is converted to String")
      val res = PGNullableLocationConverter locationToString p
      val expectedResult = "(inf,-inf)"
      Then(s"It should return a String value $expectedResult")
      res should equal(expectedResult)
    }

    scenario("String to Point Nr. 1") {
      val given = "(9.9999999999999991e-308,1e+308)"
      Given(s"a starting String: $given")
      val res = PGNullableLocationConverter stringToLocation given
      When(s"that value is converted to String $res")
      val expectedResult = new java.awt.geom.Point2D.Double(1E-307, 1E+308);
      Then(s"It should return a Point $expectedResult")
      res should equal(expectedResult)
    }

    scenario("String to Point Nr. 2") {
      val given = "(nan,nan)"
      Given(s"a starting String: $given")
      val res = PGNullableLocationConverter stringToLocation(given)
      When(s"that value is converted to String $res")
      val expectedResult = new java.awt.geom.Point2D.Double(Double.NaN, Double.NaN);
      Then(s"It should return a Point value $expectedResult")
      res.getX.isNaN && res.getY.isNaN should be(true)
    }

    scenario("String to Point Nr. 3") {
      val given = "(0,0)"
      Given(s"a starting String: $given")
      val res = PGNullableLocationConverter stringToLocation given
      When(s"that value is converted to String $res")
      val expectedResult = new java.awt.geom.Point2D.Double(0, 0)
      Then(s"It should return a String value $expectedResult")
      res should equal(expectedResult)
    }

    scenario("String to Point Nr. 4") {
      val given = "(inf,-inf)"
      Given(s"a starting String: $given")
      val res = PGNullableLocationConverter stringToLocation(given)
      When(s"that value is converted to String $res")
      val expectedResult = new java.awt.geom.Point2D.Double(Double.PositiveInfinity, Double.NegativeInfinity);
      Then(s"It should return a Point value $expectedResult")
      res should equal(expectedResult)
    }

    scenario("Int to Double and Double To Int") {
      val total = 100000
      Given(s"A random set of $total integers ")

      Then("Then double->integer and integer<- double must not fail!")
      for (num <- 0 until total) {
         val rndNum = scala.util.Random.nextInt()
         val doubleFromInt = rndNum.toDouble
//         println(doubleFromInt.toInt, rndNum)
         doubleFromInt.toInt should equal(rndNum)
//         println(rndNum, doubleFromInt)
         rndNum should equal(doubleFromInt)
      }

    }
  }
}
