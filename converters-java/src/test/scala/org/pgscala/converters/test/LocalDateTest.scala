package org.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import scala.util.Random

class LocalDateTest extends FeatureSpec with GivenWhenThen with Matchers {
  feature("about to test a LocalDate converter"){
    info("I want to test if PGNullableLocalDateConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the LocalDate special cases")

    scenario("LocalDate to String Nr. 1."){
      val t = LocalDate.now()
      Given(" a starting LocalDate value of the current date %s" format t)
      When ("that value is converted to String")
      val res = PGNullableLocalDateConverter localDateToString t
      Then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString())
    }

    scenario("LocalDate to String Nr. 2."){
      Given(" a starting LocalDate value of 1970-01-01")
      val t = LocalDate.parse("1970-01-01")
      When ("that value is converted to String")
      val res = PGNullableLocalDateConverter localDateToString t
      Then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString())
    }

    scenario("LocalDate to String Nr. 3."){
      val r = new Random
      val t = LocalDate.now().plusYears(r.nextInt(1000))
      Given(" a starting LocalDate value of the random future year %s" format t)
      When ("that value is converted to String")
      val res = PGNullableLocalDateConverter localDateToString t
      Then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString())
    }

    scenario("LocalDate to String Nr. 4."){
      Given(" a starting LocalDate value of the random past year")
      val r = new Random
      val t = LocalDate.now.minusYears(r.nextInt(LocalDate.now.getYear))
      When ("that value is converted to String")
      val res = PGNullableLocalDateConverter localDateToString t
      Then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString())
    }

    scenario("String to LocalDate Nr. 1."){
      val r = new Random
      val t = LocalDate.now()
      Given(""" a starting String value of the current date "%s""" format t)
      When ("that value is converted to LocalDate")
      val res = PGNullableLocalDateConverter stringToLocalDate t.toString
      Then ("It should return a LocalDate value %s" format res)
      res should equal(t)
    }

    scenario("String to LocalDate Nr. 2."){
      val t = "1970-01-01"
      Given(""" a starting String value of "%s""" format t)
      When ("that value is converted to LocalDate")
      val res = PGNullableLocalDateConverter stringToLocalDate t
      Then ("It should return a LocalDate value %s" format res)
      res should equal(LocalDate.parse("1970-01-01"))
    }

    scenario("String to LocalDate Nr. 3."){
      val r = new Random
      val t = LocalDate.now().plusYears(r.nextInt(1000))
      Given(""" a starting String value of the random future year "%s""" format t)
      When ("that value is converted to LocalDate")
      val res = PGNullableLocalDateConverter stringToLocalDate t.toString
      Then ("It should return a LocalDate value %s" format res)
      res should equal(t)
    }

    scenario("String to LocalDate Nr. 4."){
      val r = new Random
      val t = LocalDate.now.minusYears(r.nextInt(LocalDate.now.getYear))
      Given(""" a starting String value of the random past year "%s""" format t)
      When ("that value is converted to LocalDate")
      val res = PGNullableLocalDateConverter stringToLocalDate t.toString
      Then ("It should return a LocalDate value %s" format res)
      res should equal(t)
    }

    /*
     * POSTGRESQL
     */
    scenario("POSTGRESQL: String to LocalDate Nr. 1"){
      info("test for 'now'::date")
      val SQLdate = "2012-02-23"
      Given(""" a starting String value for sql date "%s"""" format SQLdate)
      When("that value is converted to LocalDate")
      val res = PGNullableLocalDateConverter stringToLocalDate SQLdate
      Then ("It should return a LocalDate value %s" format res.toString())
      res.toString() should equal(SQLdate)
    }

    scenario("POSTGRESQL: String to LocalDate Nr. 2"){
      info("test for 'epoch'::date")
      val SQLdate = "1970-01-01"
      Given(""" a starting String value for sql date "%s"""" format SQLdate)
      When("that value is converted to LocalDate")
      val res = PGNullableLocalDateConverter stringToLocalDate SQLdate
      Then ("It should return a LocalDate value %s" format res.toString())
      res.toString() should equal(SQLdate)
    }

    scenario("POSTGRESQL: String to LocalDate Nr. 3"){
      info("test for 'today'::date")
      val SQLdate = "2012-02-23"
      Given(""" a starting String value for sql date "%s"""" format SQLdate)
      When("that value is converted to LocalDate")
      val res = PGNullableLocalDateConverter stringToLocalDate SQLdate
      Then ("It should return a LocalDate value %s" format res.toString())
      res.toString() should equal(SQLdate)
    }

    scenario("POSTGRESQL: String to LocalDate Nr. 4"){
      info("test for 'tomorrow'::date")
      val timestamp = "2012-02-24"
      Given(""" a starting String value for sql date "%s"""" format timestamp)
      When("that value is converted to DateTime")
      val res = PGNullableLocalDateConverter stringToLocalDate timestamp
      Then ("It should return a DateTime value %s" format res.toString())
      res.toString() should equal(timestamp)
    }
  }
}
