package org.pgscala.converters

import org.joda.time.DateTime;

/** Do not edit - generated in PGBuilder / PGDateTimeConverterBuilder.scala */

object PGDateTimeConverter extends PGConverter[DateTime] {
  val PGType = PGNullableDateTimeConverter.pgType

  def toPGString(dT: DateTime) =
    PGNullableConverter.toPGString(dT)

  def fromPGString(dT: String) =
    PGNullableConverter.fromPGString(dT)
}
