package hr.element.pgscala.converters

import org.joda.time.DateTime;

/** Do not edit - generated in PGBuilder / DateTimeConverterBuilder.scala */

object PGDateTimeConverter extends PGTypeConverter[DateTime] {
  val PGType = PGNullableDateTimeConverter.pgType

  def toPGString(dT: DateTime) =
    PGNullableConverter.toPGString(dT)

  def fromPGString(dT: String) =
    PGNullableConverter.fromPGString(dT)
}
