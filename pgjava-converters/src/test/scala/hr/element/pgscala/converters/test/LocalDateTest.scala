package hr.element.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import scala.util.Random

class LocalDateTest extends FeatureSpec with GivenWhenThen with ShouldMatchers{
  feature("about to test a LocalDate converter"){
    info("I want to test if PGNullableLocalDateConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the LocalDate special cases")

    scenario("LocalDate to String Nr. 1."){
      val t = LocalDate.now()
      given(" a starting LocalDate value of the current date %s" format t)
      when ("that value is converted to String")
      val res = PGNullableLocalDateConverter localDateToString t
      then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString())
    }

    scenario("LocalDate to String Nr. 2."){
      given(" a starting LocalDate value of 1970-01-01")
      val t = LocalDate.parse("1970-01-01")
      when ("that value is converted to String")
      val res = PGNullableLocalDateConverter localDateToString t
      then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString())
    }

    scenario("LocalDate to String Nr. 3."){
      val r = new Random
      val t = LocalDate.now().plusYears(r.nextInt(1000))
      given(" a starting LocalDate value of the random future year %s" format t)
      when ("that value is converted to String")
      val res = PGNullableLocalDateConverter localDateToString t
      then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString())
    }

    scenario("LocalDate to String Nr. 4."){
      given(" a starting LocalDate value of the random past year")
      val r = new Random
      val t = LocalDate.now.minusYears(r.nextInt(LocalDate.now.getYear))
      when ("that value is converted to String")
      val res = PGNullableLocalDateConverter localDateToString t
      then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString())
    }

    scenario("String to LocalDate Nr. 1."){
      val r = new Random
      val t = LocalDate.now()
      given(""" a starting String value of the current date "%s""" format t)
      when ("that value is converted to LocalDate")
      val res = PGNullableLocalDateConverter stringToLocalDate t.toString
      then ("It should return a LocalDate value %s" format res)
      res should equal(t)
    }

    scenario("String to LocalDate Nr. 2."){
      val t = "1970-01-01"
      given(""" a starting String value of "%s""" format t)
      when ("that value is converted to LocalDate")
      val res = PGNullableLocalDateConverter stringToLocalDate t
      then ("It should return a LocalDate value %s" format res)
      res should equal(LocalDate.parse("1970-01-01"))
    }

    scenario("String to LocalDate Nr. 3."){
      val r = new Random
      val t = LocalDate.now().plusYears(r.nextInt(1000))
      given(""" a starting String value of the random future year "%s""" format t)
      when ("that value is converted to LocalDate")
      val res = PGNullableLocalDateConverter stringToLocalDate t.toString
      then ("It should return a LocalDate value %s" format res)
      res should equal(t)
    }

    scenario("String to LocalDate Nr. 4."){
      val r = new Random
      val t = LocalDate.now.minusYears(r.nextInt(LocalDate.now.getYear))
      given(""" a starting String value of the random past year "%s""" format t)
      when ("that value is converted to LocalDate")
      val res = PGNullableLocalDateConverter stringToLocalDate t.toString
      then ("It should return a LocalDate value %s" format res)
      res should equal(t)
    }

    /*
     * POSTGRESQL
     */
    scenario("POSTGRESQL: String to LocalDate Nr. 1"){
      info("test for 'now'::date")
      val SQLdate = "2012-02-23"
      given(""" a starting String value for sql date "%s"""" format SQLdate)
      when("that value is converted to LocalDate")
      val res = PGNullableLocalDateConverter stringToLocalDate SQLdate
      then ("It should return a LocalDate value %s" format res.toString())
      res.toString() should equal(SQLdate)
    }

    scenario("POSTGRESQL: String to LocalDate Nr. 2"){
      info("test for 'epoch'::date")
      val SQLdate = "1970-01-01"
      given(""" a starting String value for sql date "%s"""" format SQLdate)
      when("that value is converted to LocalDate")
      val res = PGNullableLocalDateConverter stringToLocalDate SQLdate
      then ("It should return a LocalDate value %s" format res.toString())
      res.toString() should equal(SQLdate)
    }

    scenario("POSTGRESQL: String to LocalDate Nr. 3"){
      info("test for 'today'::date")
      val SQLdate = "2012-02-23"
      given(""" a starting String value for sql date "%s"""" format SQLdate)
      when("that value is converted to LocalDate")
      val res = PGNullableLocalDateConverter stringToLocalDate SQLdate
      then ("It should return a LocalDate value %s" format res.toString())
      res.toString() should equal(SQLdate)
    }

    scenario("POSTGRESQL: String to LocalDate Nr. 4"){
      info("test for 'tomorrow'::date")
      val timestamp = "2012-02-24"
      given(""" a starting String value for sql date "%s"""" format timestamp)
      when("that value is converted to DateTime")
      val res = PGNullableLocalDateConverter stringToLocalDate timestamp
      then ("It should return a DateTime value %s" format res.toString())
      res.toString() should equal(timestamp)
    }
  }
}