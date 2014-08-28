package org.pgscala.converters

import org.joda.time.LocalDate;

/** Do not edit - generated in Builder / PGLocalDateConverterBuilder.scala */

object PGLocalDateConverter extends PGConverter[LocalDate] {
  val PGType = PGNullableLocalDateConverter.pgType

  def toPGString(lD: LocalDate) =
    PGNullableConverter.toPGString(lD)

  val defaultValue: LocalDate = LocalDate.parse("0001-01-01")

  def fromPGString(lD: String) =
    if (lD eq null) defaultValue else
    PGNullableConverter.fromPGString(lD)
}
