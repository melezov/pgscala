package org.pgscala.converters
package test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class ByteArrayTest extends FeatureSpec with GivenWhenThen with ShouldMatchers {
  feature("About to test an String converter"){
    info("I want to test if PGNullableByteArrayConverter works correctly, both in 2 way conversion")
    info("I am going to perform tests for the String boundary cases")

    scenario("String to Array[Byte] Nr. 1 test and back") {
      info("test for select ''::bytea;")
      Given ("a starting value of zero String length")
      val s = ""
      When ("that value is transformed to Array[Byte] and back to String")
      val bA  = PGNullableByteArrayConverter.stringToByteArray(s)
      val res = PGNullableByteArrayConverter.byteArrayToString(bA)
      Then ("it should return an String value %s" format res)
      res should equal(s)
    }

    scenario("String to Array[Byte] Nr. 2 test and back") {
      info("test for select '\\xabcdef0123456789'::bytea;")
      val s = "\\xabcdef0123456789"
      Given ("""a starting String value of  "%s"""" format s)
      When ("that value is transformed to Array[Byte] and back to String")
      val bA  = PGNullableByteArrayConverter.stringToByteArray(s)
      val res = PGNullableByteArrayConverter.byteArrayToString(bA)
      Then ("it should return an String value %s" format res)
      res should equal(s)
    }

    scenario("String to Array[Byte] Nr. 3 test and back") {
      info("test for select '\\xaaccccddff1234'::bytea;")
      val s = "\\xaaccccddff1234"
      Given ("""a starting String value of  "%s"""" format s)
      When ("that value is transformed to Array[Byte] and back to String")
      val bA  = PGNullableByteArrayConverter.stringToByteArray(s)
      val res = PGNullableByteArrayConverter.byteArrayToString(bA)
      Then ("it should return an String value %s" format res)
      res should equal(s)
    }

    scenario("String to Array[Byte] Nr. 4 test and back") {
      info("test for select '\\xbbbb012345'::bytea;")
      val s = "\\xbbbb012345"
      Given ("""a starting String value of  "%s"""" format s)
      When ("that value is transformed to Array[Byte] and back to String")
      val bA = PGNullableByteArrayConverter.stringToByteArray(s)
      val res = PGNullableByteArrayConverter.byteArrayToString(bA)
      Then ("it should return an String value %s" format res)
      res should equal(s)
    }

    scenario("Array[Byte] to String Nr. 1 test and back"){
      info("test for some random number procesing 10K")
      val randc = new Random(33)
      val arr   = new Array[Byte](10000)
      randc.nextBytes(arr)
      Given ("a starting Array[Byte] value of %s" format arr)
      When ("that value is transformed to String and back")
      val str1 = PGNullableByteArrayConverter.byteArrayToString(arr)
      val arr2 = PGNullableByteArrayConverter.stringToByteArray(str1)
      Then ("it should return an Array[byte] value %s" format arr2)
      arr should equal (arr2)
    }

    scenario("Array[Byte] to String Nr. 2 test and back"){
      info("test for some random number procesing 1M")
      val randc = new Random(33)
      val arr   = new Array[Byte](1000000)
      randc.nextBytes(arr)
      Given ("a starting Array[Byte] value of  %s" format arr)
      When ("that value is transformed to String and back")
      val str1 = PGNullableByteArrayConverter.byteArrayToString(arr)
      val arr2 = PGNullableByteArrayConverter.stringToByteArray(str1)
      Then ("it should return an Array[byte] value %s" format arr2)
      arr should equal (arr2)
    }
  }
}
