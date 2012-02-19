package hr.element.pgscala.converters

import org.joda.time.DateTime

object PGDateTimeConverter extends PGTypeConverter[DateTime] {
  val PGType = PGNullableDateTimeConverter.pgType

  def toPGString(dT: DateTime): String =
    PGNullableConverter.toPGString(dT)

  def fromPGString(dT: String): DateTime =
    PGNullableConverter.fromPGString(dT)
}
