package hr.element.pgscala.converters

import org.joda.time.LocalDate;

object PGLocalDateConverter extends PGTypeConverter[LocalDate] {
  val PGType = PGNullableLocalDateConverter.pgType

  def toPGString(lD: LocalDate) =
    PGNullableConverter.toPGString(lD)

  def fromPGString(lD: String) =
    PGNullableConverter.fromPGString(lD)
}
