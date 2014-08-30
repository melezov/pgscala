package org.pgscala.converters
package test

import org.pgscala.util._

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers

import org.joda.time._

@RunWith(classOf[JUnitRunner])
class PGRecordPackSpec
    extends FeatureSpec with GivenWhenThen with Matchers {

  feature("record unpacking") {
    scenario("can read simple record") {
      val record = "(\"NULL\",,\"\")"
      val items = PGRecord.unpack(record)
      items(0) should be { "NULL" }
      items(1) should be { null }
      items(2) should be { "" }
    }

    scenario("can read simple record with NULL string value") {
      val record = "(NULL,,\"\")"
      val items = PGRecord.unpack(record)
      items(0) should be { "NULL" }
      items(1) should be { null }
      items(2) should be { "" }
    }

    scenario("can unpack none date") {
      val record = "()"
      val items = PGRecord.unpack(record)
      val date = PGOptionLocalDateConverter.fromPGString(items(0))
      date should be { None }
    }

    scenario("can unpack null date") {
      val record = "()"
      val items = PGRecord.unpack(record)
      val date = PGLocalDateConverter.fromPGString(items(0))
      date should be { LocalDate.parse("1-1-1") }
    }

    scenario("can unpack date") {
      val record = "(2011-02-03)"
      val items = PGRecord.unpack(record)
      val date = PGLocalDateConverter.fromPGString(items(0))
      date should be { new LocalDate(2011, 2, 3) }
    }

    scenario("can unpack date time and int") {
      val record = """("2012-01-02 20:56:15.017+01",3)"""
      val items = PGRecord.unpack(record)
      val time = PGOptionDateTimeConverter.fromPGString(items(0))
      val number = PGIntConverter.fromPGString(items(1))
      //time.get should be { DateTime.parse("2012-1-2T20:56:15.017+01") } // WTF?!?
      time.toString should be { "Some(2012-01-02T20:56:15.017+01:00)" }
      number should be { 3 }
    }
  }

  feature("record packing") {
    scenario("can write simple record") {
      val list = List("NULL", null, "")
      val record = PGRecord.pack(list.toArray)
      record should be { "(NULL,,\"\")" }
    }

    scenario("can pack date") {
      val list = List(PGLocalDateConverter.toPGString(new LocalDate(2011, 2, 3)))
      val record = PGRecord.pack(list.toArray)
      record should be { "(2011-02-03)" }
    }

    scenario("can pack date time and int") {
      val list =
        List(
          PGDateTimeConverter.toPGString(new DateTime(2012, 1, 2, 20, 56, 15, 17)),
          PGIntConverter.toPGString(3))
      val record = PGRecord.pack(list.toArray)
      record should be { """("2012-01-02 20:56:15.017000+01:00",3)""" }
    }
  }

  feature("array packing") {
    scenario("can write simple array") {
      val list = List("1", null, "2")
      val array = PGArray.pack(list.toArray)
      array should be { "{1,NULL,2}" }
    }

    scenario("can escape array elements") {
      val list = List("a\"b", null, "")
      val array = PGArray.pack(list.toArray)
      array should be { "{\"a\\\"b\",NULL,\"\"}" }
    }
  }
}
