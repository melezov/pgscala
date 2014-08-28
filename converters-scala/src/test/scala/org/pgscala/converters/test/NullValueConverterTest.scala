package org.pgscala
package converters
package test

import java.sql.{Statement, ResultSet}
import org.pgscala.converters._
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}
import org.joda.time._

class NullValueCoreTest extends FeatureSpec
                        with GivenWhenThen
                        with Matchers {

  feature("when a record is null") {
    scenario("conversion to BigDecimal will be 0") {
      val record = null
      val converted = PGBigDecimalConverter.fromPGString(record)
      converted should  be { scala.math.BigDecimal(0) }
    }

    scenario("conversion to BigInt will be 0") {
      val record = null
      val converted = PGBigIntConverter.fromPGString(record)
      converted should  be { scala.math.BigInt(0) }
    }

    scenario("conversion to Boolean will be false") {
      val record = null
      val converted = PGBooleanConverter.fromPGString(record)
      converted should  be { false }
    }

    scenario("conversion to ByteArray will be empty array") {
      val record = null
      val converted = PGByteArrayConverter.fromPGString(record)
      converted should  be { Array.empty[Byte]}
    }

    scenario("conversion to DateTime will be 0001.1.1 00.00") {
      val record = null
      val converted = PGDateTimeConverter.fromPGString(record)
      converted.toString should be { "0001-01-01T00:00:00.000Z"}
    }

    scenario("conversion to Double will be 0d") {
      val record = null
      val converted = PGDoubleConverter.fromPGString(record)
      converted should  be { 0d }
    }

    scenario("conversion to Elem will be null") {
      val record = null
      val converted = PGElemConverter.fromPGString(record)
      converted should  be { null }
    }

    scenario("conversion to Float will be 0") {
      val record = null
      val converted = PGFloatConverter.fromPGString(record)
      converted should  be { 0f }
    }

    scenario("conversion to Int will be 0") {
      val record = null
      val converted = PGIntConverter.fromPGString(record)
      converted should  be { 0 }
    }

    scenario("conversion to LocalDate is 0001.1.1") {
      val record = null
      val converted = PGLocalDateConverter.fromPGString(record)
      converted.toString should be { "0001-01-01" }
    }

    scenario("conversion to Long will be 0") {
      val record = null
      val converted = PGLongConverter.fromPGString(record)
      converted should  be { 0 }
    }

    scenario("conversion to Map will be empty map") {
      val record = null
      val converted = PGMapConverter.fromPGString(record)
      converted should  be { Map.empty }
    }

    scenario("conversion to Short will be 0") {
      val record = null
      val converted = PGShortConverter.fromPGString(record)
      converted should  be { 0 }
    }

    scenario("conversion to String will empty string") {
      val record = null
      val converted = PGStringConverter.fromPGString(record)
      converted should  be { "" }
    }

    scenario("conversion to UUID will be 0-0-0-0") {
      val record = null
      val converted = PGUUIDConverter.fromPGString(record)
      converted should  be { java.util.UUID.fromString("0-0-0-0-0") }
    }
  }
}
