package org.pgscala.converters

import org.joda.time.DateTime

/** Do not edit - generated in Builder / PGDateTimeConverterBuilder.scala */

object PGDateTimeConverter extends PGConverter[DateTime] {
  val PGType = PGNullableDateTimeConverter.pgType

  def toPGString(dt: DateTime) =
    PGNullableDateTimeConverter.dateTimeToString(dt)

  val defaultValue: DateTime = DateTime.parse("0001-01-01T00:00:00Z")

  def fromPGString(dt: String) =
    if (dt eq null)
      defaultValue
    else
      PGNullableDateTimeConverter.stringToDateTime(dt)
}
