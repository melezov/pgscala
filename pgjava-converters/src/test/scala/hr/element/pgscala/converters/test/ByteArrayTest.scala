//package hr.element.pgscala.converters.test
//import org.scalatest.FunSuite
//import scala.collection.mutable.Stack
//import hr.element.pgscala.converters.PGNullableByteArrayConverter
//import org.scalatest.matchers.ShouldMatchers
//import scala.util.Random
//
//class ExampleSuite extends FunSuite
//  with ShouldMatchers {
//
//
// test("String to byteArray test and back, 0 str length") {
//
//    val s = ""
//    val a = PGNullableByteArrayConverter.unquoteByteArray(s)
//    //println
//    //a.foreach(x => print("+" + x))
//    val s1 = PGNullableByteArrayConverter.quoteByteArray(a)
//    //println(" t>" + s + ", " + s1)
//
//    s should equal(s1)
//
//  }
//  test("String to byteArray test and back") {
//
//    val s = "\\xabcdef0123456789"
//    val a = PGNullableByteArrayConverter.unquoteByteArray(s)
//    //println
//    //a.foreach(x => print("+" + x))
//    val s1 = PGNullableByteArrayConverter.quoteByteArray(a)
//    //println(" t>" + s + ", " + s1)
//
//    s should equal(s1)
//
//  }
//
//  test("String to byteArray test and back 2") {
//
//    val s = "\\xaaccccddff1234"
//    val a = PGNullableByteArrayConverter.unquoteByteArray(s)
//    //println
//    //a.foreach(x => print("+" + x))
//    val s1 = PGNullableByteArrayConverter.quoteByteArray(a)
//    //println(" t>" + s + ", " + s1)
//
//    s should equal(s1)
//
//  }
//  test("String to byteArray test and back 3") {
//
//    val s = "\\xbbbb012345"
//    val a = PGNullableByteArrayConverter.unquoteByteArray(s)
//    //println
//    //a.foreach(x => print("+" + x))
//    val s1 = PGNullableByteArrayConverter.quoteByteArray(a)
//    //println(" t>" + s + ", " + s1)
//
//    s should equal(s1)
//
//  }
//
//
//    test("String to byteArray test and back 4") {
//
//    val s = "\\xbbbb012345"
//    val a = PGNullableByteArrayConverter.unquoteByteArray(s)
//    //println
//    //a.foreach(x => print("+" + x))
//    val s1 = PGNullableByteArrayConverter.quoteByteArray(a)
//    //println(" t>" + s + ", " + s1)
//
//    s should equal(s1)
//
//  }
//    test("Some random number procesing 10K."){
//      val randc = new Random(33)
//      val arr   = new Array[Byte](10000)
//      randc.nextBytes(arr)
//
//      val str1 = PGNullableByteArrayConverter.quoteByteArray(arr)
//      //println(str1)
//      val arr2 = PGNullableByteArrayConverter.unquoteByteArray(str1)
//
//      arr should equal (arr2)
//    }
//        test("Some random number procesing 1M."){
//      val randc = new Random(33)
//      val arr   = new Array[Byte](1000000)
//      randc.nextBytes(arr)
//
//      val str1 = PGNullableByteArrayConverter.quoteByteArray(arr)
//      //println(str1)
//      val arr2 = PGNullableByteArrayConverter.unquoteByteArray(str1)
//
//      arr should equal (arr2)
//    }
//}
