package org.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat



class DateTimeTest extends FeatureSpec with GivenWhenThen with Matchers {
  val dFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSZZ")

  feature("About to test a DateTime converter"){
    info("I want to test if PGNullableDateTimeConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the DateTime special timestamp inputs")

    scenario("Datetime to String Nr. 1."){
      val t = DateTime.now()
      Given(" a starting current DateTime value %s" format t)
      When ("that value is converted to String")
      val res = PGNullableDateTimeConverter dateTimeToString t
      Then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString())
    }

    scenario("Datetime to String Nr. 2."){
      val t = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss").parseDateTime("1970-01-01 00:00:00")
      Given(" a starting DateTime value of epoch %s" format t)
      When ("that value is converted to String")
      val res = PGNullableDateTimeConverter dateTimeToString t
      Then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString())
    }

    scenario("Datetime to String Nr. 3."){
      val t = LocalDate.now().toDateTimeAtStartOfDay()
      Given(" a starting current DateTime value at midnight %s" format t)
      When ("that value is converted to String")
      val res = PGNullableDateTimeConverter dateTimeToString t
      Then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString())
    }

    scenario("Datetime to String Nr. 4."){
      val t = DateTime.now().plus(1)
      Given(" a starting DateTime value for tomorrow %s" format t)
      When ("that value is converted to String")
      val res = PGNullableDateTimeConverter dateTimeToString t
      Then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString())
    }

    scenario("Datetime to String Nr. 5."){
      val t = DateTime.now().minus(1)
      Given(" a starting DateTime value for yesterday %s" format t)
      When ("that value is converted to String")
      val res = PGNullableDateTimeConverter dateTimeToString t
      Then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString())
    }

    scenario("String to DateTime Nr. 1"){
      val t = DateTime.now()
      Given(" a starting String value of current DateTime %s" format t)
      When ("that value is converted to DateTime")
      val res = PGNullableDateTimeConverter stringToDateTime dFormat.print(t)
      Then ("It should return a DateTime value %s" format res.toString())
      res should equal(t)
    }

    scenario("String to DateTime Nr. 2"){
      val t = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss").parseDateTime("1970-01-01 00:00:00")
      Given(""" a starting String value of epoch "%s"""" format t)
      When ("that value is converted to DateTime")
      val res = PGNullableDateTimeConverter stringToDateTime dFormat.print(t)
      Then ("It should return a DateTime value %s" format res.toString())
      res should equal(t)
    }

    scenario("String to DateTime Nr. 3"){
      val t = LocalDate.now().toDateTimeAtStartOfDay
      Given(""" a starting String value of current date at midnight "%s""" format t)
      When ("that value is converted to DateTime")
      val res = PGNullableDateTimeConverter stringToDateTime dFormat.print(t)
      Then ("It should return a DateTime value %s" format res.toString())
      res should equal(t)
    }

    scenario("String to DateTime Nr. 4"){
      val t = DateTime.now().plus(1)
      Given(""" a starting String value of tomorrow's date and time "%s""" format t)
      When ("that value is converted to DateTime")
      val res = PGNullableDateTimeConverter stringToDateTime dFormat.print(t)
      Then ("It should return a DateTime value %s" format res.toString())
      res should equal(t)
    }

    scenario("String to DateTime Nr. 5"){
      val t = DateTime.now().minus(1)
      Given(""" a starting String value of yesterday's date and time "%s""" format t)
      When ("that value is converted to DateTime")
      val res = PGNullableDateTimeConverter stringToDateTime dFormat.print(t)
      Then ("It should return a DateTime value %s" format res.toString())
      res should equal(t)
    }

    /*
     * POSTGRESQL
     */
    scenario("POSTGRESQL: String to DateTime Nr. 1"){
      info("test for 'now'::timestamp")
      val SQLtimestamp = "2012-02-23 14:27:01.994854"
      Given(""" a starting String value for sql timestamp "%s""" format SQLtimestamp)
      When("that value is converted to DateTime")
      val res = PGNullableDateTimeConverter stringToDateTime SQLtimestamp
      Then ("It should return a DateTime value %s" format res.toString())
      res.toString() should equal(SQLtimestamp)
    }

    scenario("POSTGRESQL: String to DateTime Nr. 2"){
      info("test for 'epoch'::timestamp")
      val SQLtimestamp = "1970-01-01 00:00:00"
      Given(""" a starting String value for sql timestamp "%s""" format SQLtimestamp)
      When("that value is converted to DateTime")
      val res = PGNullableDateTimeConverter stringToDateTime SQLtimestamp
      Then ("It should return a DateTime value %s" format res.toString())
      res.toString() should equal(SQLtimestamp)
    }

    scenario("POSTGRESQL: String to Datetime Nr. 3"){
      info("test for 'today'::timestamp")
      val SQLtimestamp = "2012-02-23 00:00:00"
      Given(""" a starting String value for sql timestamp "%s""" format SQLtimestamp)
      When("that value is converted to DateTime")
      val res = PGNullableDateTimeConverter stringToDateTime SQLtimestamp
      Then ("It should return a DateTime value %s" format res.toString())
      res.toString() should equal(SQLtimestamp)
    }

    scenario("POSTGRESQL: String to Datetime Nr. 4"){
      info("test for 'tomorrow'::timestamp")
      val SQLtimestamp = "2012-02-24 00:00:00"
      Given(""" a starting String value for sql timestamp "%s""" format SQLtimestamp)
      When("that value is converted to DateTime")
      val res = PGNullableDateTimeConverter stringToDateTime SQLtimestamp
      Then ("It should return a DateTime value %s" format res.toString())
      res.toString() should equal(SQLtimestamp)
    }

    scenario("POSTGRESQL: String to Datetime Nr. 5"){
      info("test for timezone: SELECT TIMESTAMP WITH TIME ZONE 'now';")
      val SQLtimestamp = "2012-02-23 15:49:39.466516+01"
      Given(""" a starting String value for sql timestamp "%s""" format SQLtimestamp)
      When("that value is converted to DateTime")
      val res = PGNullableDateTimeConverter stringToDateTime SQLtimestamp
      Then ("It should return a DateTime value %s" format res.toString())
      res.toString() should equal(SQLtimestamp)
    }


   }
}
