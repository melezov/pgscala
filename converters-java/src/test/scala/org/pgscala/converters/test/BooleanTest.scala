package org.pgscala.converters
package test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers

@RunWith(classOf[JUnitRunner])
class BooleanTest extends FeatureSpec with GivenWhenThen with Matchers {
  feature("About to test a boolean converter") {
    info("I want to test if PGNullableBooleanConverter works correctly, both in 2 way conversion")

    scenario("boolean to string Nr. 1") {
      Given("a starting boolean value of true")
      val t = true
      When("that value is converted to String")
      val res = PGNullableBooleanConverter booleanToString t
      Then("""It should return a String value "t"""")
      res should equal("t")
    }

    scenario("boolean to string Nr. 2") {
      Given("a starting boolean value of false")
      val f = false
      When("that value is converted to String")
      val res = PGNullableBooleanConverter booleanToString f
      Then("""It should return a String value "f"""")
      res should equal("f")
    }

    scenario("string to boolean Nr. 1") {
      Given("""a starting String value of "t"""")
      val t = "t"
      When("that value is converted to String")
      val res = PGNullableBooleanConverter stringToBoolean t
      Then("""It should return a boolean value true""")
      res should equal(true)
    }

    scenario("string to boolean Nr. 2") {
      Given("""a starting String value of "f"""")
      val f = "f"
      When("that value is converted to String")
      val res = PGNullableBooleanConverter stringToBoolean f
      Then("""It should return a boolean value false""")
      res should equal(false)
    }
  }
}
