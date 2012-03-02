package hr.element.pgscala
package converters
package test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import java.util.UUID;
import scala.util.Random


class UUIDTest extends FeatureSpec with GivenWhenThen with ShouldMatchers{
  feature("About to test an UUID converter"){
    info("I want to test if PGNullableUUIDConverter works correctly, both in 2 way conversion")

    scenario("UUID to String Nr. 1."){
      val t = UUID.randomUUID
      given(" a starting random UUID value %s" format t)
      when ("that value is converted to String")
      val res = PGNullableUUIDConverter uuidToString t
      then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString)
    }

    scenario("UUID to String Nr. 2."){
      val randc = new Random(33)
      val arr = new Array[Byte] (1000000)
      randc.nextBytes(arr)
      val t   = UUID.nameUUIDFromBytes(arr)

      given(" a starting random UUID value %s" format t)
      when ("that value is converted to String")
      val res = PGNullableUUIDConverter uuidToString t
      then ("""It should return a String value "%s"""" format res)
      res should equal(t.toString)
    }

    scenario("String to UUID Nr. 1."){
      info("test for select 'c4c82f7e-8550-4d05-829b-fa44d5fc0546'::uuid;")
      val t = "c4c82f7e-8550-4d05-829b-fa44d5fc0546"
      given(""" a starting String value "%s""" format t)
      when ("that value is converted to UUID")
      val res = PGNullableUUIDConverter stringToUUID t
      then ("It should return a UUID value %s" format res)
      res.toString should equal(t)
    }

  }

}