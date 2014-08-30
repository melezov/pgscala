package org.pgscala.converters

import org.joda.time.LocalDate

/** Do not edit - generated in Builder / PGLocalDateConverterBuilder.scala */

object PGLocalDateConverter extends PGConverter[LocalDate] {
  val PGType = PGNullableLocalDateConverter.pgType

  def toPGString(ld: LocalDate) =
    PGNullableLocalDateConverter.localDateToString(ld)

  val defaultValue: LocalDate = LocalDate.parse("0001-01-01")

  def fromPGString(ld: String) =
    if (ld eq null)
      defaultValue
    else
      PGNullableLocalDateConverter.stringToLocalDate(ld)
}
