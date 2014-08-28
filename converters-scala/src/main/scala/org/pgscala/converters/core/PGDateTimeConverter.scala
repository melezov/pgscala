package org.pgscala.converters

import org.joda.time.DateTime;

/** Do not edit - generated in Builder / PGDateTimeConverterBuilder.scala */

object PGDateTimeConverter extends PGConverter[DateTime] {
  val PGType = PGNullableDateTimeConverter.pgType

  def toPGString(dT: DateTime) =
    PGNullableConverter.toPGString(dT)

  val defaultValue: DateTime = DateTime.parse("0001-01-01T00:00:00Z")

  def fromPGString(dT: String) =
    if (dT eq null) defaultValue else
    PGNullableConverter.fromPGString(dT)
}
