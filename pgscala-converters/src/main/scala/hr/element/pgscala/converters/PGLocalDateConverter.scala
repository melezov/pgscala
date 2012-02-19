package hr.element.pgscala.converters

import org.joda.time.LocalDate

object PGLocalDateConverter extends PGTypeConverter[LocalDate] {
  val PGType = PGNullableLocalDateConverter.pgType

  def toPGString(lD: LocalDate): String =
    PGNullableConverter.toPGString(lD)

  def fromPGString(lD: String): LocalDate =
    PGNullableConverter.fromPGString(lD)
}
