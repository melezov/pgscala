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

    scenario("Double Point to String Nr. 1") {
      Given("a starting Point(Double.Min, Double.MaxValue)")
      val p = new java.awt.geom.Point2D.Double(Double.MaxValue, Double.MinValue);
      val res = PGNullablePointConverter pointToString p
      When(s"that value is converted to String $res")
      val expectedResult = s"(${Double.MaxValue.toDouble},${Double.MinValue.toDouble})"
      Then(s"It should return a String value $expectedResult")
      res should equal(expectedResult)
    }
    
    scenario("Double Point to String Nr. 2") {
      Given("a starting Point(Double.NaN, Double.NaN)")
      val p = new java.awt.geom.Point2D.Double(Double.NaN, Double.NaN);
      When("that value is converted to String")
      val res = PGNullablePointConverter pointToString p
      val expectedResult = s"(${Double.NaN},${Double.NaN})"
      Then(s"It should return a String value $expectedResult")
      res should equal(expectedResult)
    }    

    scenario("Double Point to String Nr. 3") {
      Given("a starting Point(0,0)")
      val p = new java.awt.geom.Point2D.Double(0, 0);
      val res = PGNullablePointConverter pointToString p
      When(s"that value is converted to String $res")
      val expectedResult = s"(${0d},${0d})"
      Then(s"It should return a String value $expectedResult")
      res should equal(expectedResult)
    }
    
    scenario("Double Point to String Nr. 4") {
      Given("a starting Point(Double.PositiveInfinity, Double.NegativeInfinity)")
      val p = new java.awt.geom.Point2D.Double(Double.PositiveInfinity, Double.NegativeInfinity);
      When("that value is converted to String")
      val res = PGNullablePointConverter pointToString p
      val expectedResult = s"(${Double.PositiveInfinity},${Double.NegativeInfinity})"
      Then(s"It should return a String value $expectedResult")
      res should equal(expectedResult)
    } 
    
//    scenario("Db test Nr. 1") {
//      PGTestDb.
//    }  
    
    scenario("String to Point Nr. 1") {
      Given("a starting String (Double.Min, Double.MaxValue)")
      val given = s"(${Double.MaxValue.toDouble},${Double.MinValue.toDouble})"
      val res = PGNullablePointConverter stringToPoint given
      When(s"that value is converted to String $res")
      val expectedResult = new java.awt.geom.Point2D.Double(Double.MaxValue, Double.MinValue);
      Then(s"It should return a Point $expectedResult")
      res should equal(expectedResult)
    }
    
    scenario("String to Point Nr. 2") {
      Given("a starting String (Double.NaN, Double.NaN)")
      val given = s"(${Double.NaN},${Double.NaN})"
      val res = PGNullablePointConverter stringToPoint(given)
      When(s"that value is converted to String $res")
      val expectedResult = new java.awt.geom.Point2D.Double(Double.NaN, Double.NaN);
      Then(s"It should return a Point value $expectedResult")
      res should equal(expectedResult)
    }    

    scenario("String to Point Nr. 3") {
      Given("a starting Point(0,0)")
      val given = s"(${0d},${0d})"
      val res = PGNullablePointConverter stringToPoint given
      When(s"that value is converted to String $res")
      val expectedResult = new java.awt.geom.Point2D.Double(0, 0)
      Then(s"It should return a String value $expectedResult")
      res should equal(expectedResult)
    }
    
    scenario("String to Point Nr. 4") {
      Given("a starting Point(Double.PositiveInfinity, Double.NegativeInfinity)")
      val given = s"(${Double.PositiveInfinity},${Double.NegativeInfinity})"
      val res = PGNullablePointConverter stringToPoint(given)
      When(s"that value is converted to String $res")
      val expectedResult = new java.awt.geom.Point2D.Double(Double.PositiveInfinity, Double.NegativeInfinity);
      Then(s"It should return a Point value $expectedResult")
      res should equal(expectedResult)
    }     
  }
}
