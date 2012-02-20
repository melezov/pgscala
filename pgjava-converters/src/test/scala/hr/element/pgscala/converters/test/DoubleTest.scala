package hr.element.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers

class DoubleTest extends FeatureSpec with GivenWhenThen with ShouldMatchers{
  feature("About to test a Double converter"){
    info("I want to test if PGNullableDoubleConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the Double edge conditions")

    scenario("Double to String Nr. 1"){
      given ("a starting Double value of positive infinity")
      val d = Double.PositiveInfinity

    }

  }
}