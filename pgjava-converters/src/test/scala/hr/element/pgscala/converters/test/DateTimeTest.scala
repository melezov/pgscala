package hr.element.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat


class DateTimeTest extends FeatureSpec with GivenWhenThen with ShouldMatchers{

  feature("About to test a DateTime converter"){
    info("I want to test if PGNullableDateTimeConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the DateTime special timestamp inputs")

    scenario("Datetime to String Nr. 1."){
      given(" a starting timestamp value of epoch")

      pending
    }
  }
}