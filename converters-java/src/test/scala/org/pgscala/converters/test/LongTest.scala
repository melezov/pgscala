package org.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers
import scala.util.Random

class LongTest extends FeatureSpec with GivenWhenThen with Matchers {
  feature("About to test an Long converter"){
    info("I want to test if PGNullableLongConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the Long boundary cases")

    scenario("Long to String Nr. 1"){
      Given ("a starting Long value of Long.MaxValue")
      val n = Long.MaxValue
      When ("that value is converted to String")
      val res = PGNullableLongConverter longToString n
      Then ("""It should return a String value "%d"""" format n)
      res should equal(n.toString)
     }
    scenario("Long to String Nr. 2"){
      Given ("a starting Long value of Long.MinValue")
      val n = Long.MinValue
      When ("that value is converted to String")
      val res = PGNullableLongConverter longToString n
      Then ("""It should return a String value "%d"""" format n)
      res should equal(n.toString)
    }
    scenario("Long to String Nr. 3"){
      Given ("a starting Long value of 0")
      val n = 0L
      When ("that value is converted to String")
      val res = PGNullableLongConverter longToString n
      Then ("""It should return a String value "%d"""" format n)
      res should equal(n.toString)
    }
    scenario("Long to String Nr. 4"){
      val r = Random
      val n = r.nextLong
      Given ("a starting random Long value of %d" format n)
      When ("that value is converted to String")
      val res = PGNullableLongConverter longToString n
      Then ("""It should return a String value "%d"""" format n)
      res should equal(n.toString)
    }

    scenario("String to Long Nr. 1"){
      info("test for select 9223372036854775807::bigint;")
      Given ("a starting String value of Long.MaxValue")
      val n = "9223372036854775807"
      When ("that value is converted to Long")
      val res = PGNullableLongConverter stringToLong n
      Then ("It should return an Long value %s" format n)
      res.toString should equal(n)
    }
    scenario("String to Long Nr. 2"){
      info("test for select  -9223372036854775808;")
      Given ("a starting String value of Long.MinValue")
      val n = "-9223372036854775808"
      When ("that value is converted to Long")
      val res = PGNullableLongConverter stringToLong n
      Then ("It should return an Long value %s" format n)
      res.toString should equal(n)
    }
    scenario("String to Long Nr. 3"){
      info("test for select 0::bigint;")
      Given ("""a starting String value of "0"""")
      val n = "0"
      When ("that value is converted to Long")
      val res = PGNullableLongConverter stringToLong n
      Then ("It should return an Long value %s" format n)
      res.toString should equal(n)
    }
    scenario("String to Long Nr. 4"){
      val r = Random
      val n = r.nextLong.toString
      Given ("""a starting String value of random Long number "%s"""" format n)
      When ("that value is converted to Long")
      val res = PGNullableLongConverter stringToLong n
      Then ("It should return an Long value %s" format n)
      res.toString should equal(n)
    }
  }
}
