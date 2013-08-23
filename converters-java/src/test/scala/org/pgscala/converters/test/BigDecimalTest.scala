package org.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import java.math.BigDecimal
import scala.util.Random

class BigDecimalTest extends FeatureSpec with GivenWhenThen with ShouldMatchers {

  feature("about to test a BigDecimal converter"){
    info("I want to test if PGNullableBigDecimalConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the BigDecimal special cases")

    scenario("BigDecimal to String Nr. 1"){
      //select 3.1415926530119026040722614947737296840070086399613::numeric;
      val bd = BigDecimal.valueOf(103993).divide(BigDecimal.valueOf(33102), new java.math.MathContext(50))
      Given ("a starting BigDecimal value of irrational number %s" format bd)
      When ("that value is converted to String")
      val res = PGNullableBigDecimalConverter bigDecimalToString bd
      Then ("""It should return a String value "%s"""" format res)
      res should equal (bd.toString)
    }

    scenario("BigDecimal to String Nr. 2"){
      val bd = BigDecimal.ZERO
      Given ("a starting BigDecimal value of %s" format bd)
      When ("that value is converted to String")
      val res = PGNullableBigDecimalConverter bigDecimalToString bd
      Then ("""It should return a String value "%s"""" format res)
      res should equal (bd.toString)
    }

    scenario("BigDecimal to String Nr. 3"){
      val bd = BigDecimal.ONE
      Given ("a starting BigDecimal value of %s" format bd)
      When ("that value is converted to String")
      val res = PGNullableBigDecimalConverter bigDecimalToString bd
      Then ("""It should return a String value "%s"""" format res)
      res should equal (bd.toString)
    }

    scenario("BigDecimal to String Nr. 4"){
      //select 0.33333333333333333333333333333333333333333333333333::decimal;
      val bd = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(3), new java.math.MathContext(50))
      Given ("a starting BigDecimal value for rational number %s" format bd)
      When ("that value is converted to String")
      val res = PGNullableBigDecimalConverter bigDecimalToString bd
      Then ("""It should return a String value "%s"""" format res)
      res should equal (bd.toString)
    }

    scenario("String to BigDecimal Nr. 1"){
      val s = "0"
      Given("""a starting String value of "%s""" format s)
      When("that value is converted to BigDecimal")
      val res = PGNullableBigDecimalConverter stringToBigDecimal s
      Then("it should return a BigDecimal value %s" format res)
      res should equal (BigDecimal.ZERO)
    }

    scenario("String to BigDecimal Nr. 2"){
      val s = "1"
      Given("""a starting String value of "%s""" format s)
      When("that value is converted to BigDecimal")
      val res = PGNullableBigDecimalConverter stringToBigDecimal s
      Then("it should return a BigDecimal value %s" format res)
      res should equal (BigDecimal.ONE)
    }

    scenario("String to BigDecimal Nr. 3"){
      val s = "0.33333333333333333333333333333333333333333333333333"
      val bd = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(3), new java.math.MathContext(50))
      Given("""a starting String value of "%s""" format s)
      When("that value is converted to BigDecimal")
      val res = PGNullableBigDecimalConverter stringToBigDecimal s
      Then("it should return a BigDecimal value %s" format res)
      res should equal (bd)
    }

    scenario("String to BigDecimal Nr. 4"){
      val s = "3.1415926530119026040722614947737296840070086399613"
      val bd =  BigDecimal.valueOf(103993).divide(BigDecimal.valueOf(33102), new java.math.MathContext(50))
      Given("""a starting String value of "%s""" format s)
      When("that value is converted to BigDecimal")
      val res = PGNullableBigDecimalConverter stringToBigDecimal s
      Then("it should return a BigDecimal value %s" format res)
      res should equal (bd)
    }
  }
}
