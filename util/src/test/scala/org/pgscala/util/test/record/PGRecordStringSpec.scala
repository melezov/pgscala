package org.pgscala.util
package test
package record

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class PGRecordStringSpec
    extends FeatureSpec with GivenWhenThen with ShouldMatchers {

  feature("Strings can be quoted into record string literals") {

    val origStr = """It's OK -> \ Don"t {worry} be (happy)! /"""
    val quotStr = """"It's OK -> \\ Don""t {worry} be (happy)! /""""

    scenario("String can be quoted") {
      PGRecord.quote(origStr) should equal (quotStr)
    }

    scenario("Boundary conditions must satisfy preset rules") {
      info("The quoteString must return an empty string on null input")
      PGRecord.quote(null: String) should equal ("")

      info("""The quoteString must return "" on empty string input""")
      PGRecord.quote("") should equal ("\"\"")
    }

    scenario("Strings can be quoted to be directly embedded into queries") {
      val testSchema = "test_schema[%08x:%04x]" format(
        System.currentTimeMillis(), scala.util.Random.nextInt()
      )

      PGTestDb.testInSchema(testSchema){
        implicit val schema = Some(testSchema)

        info("Testing in schema [%s] ..." format PGIdent.quote(testSchema))

        PGTestDb.iud("""
          CREATE TYPE oneStr AS (s text);
        """)

        PGTestDb.qry("""
          SELECT (%s::oneStr).s;
        """ format (
          PGLiteral.quote("(%s)" format(
            PGRecord.quote(origStr)
          ))
        )){ rS =>
          rS.next()
          rS.getString(1) should equal (origStr)
        }
      }
    }
  }
}
