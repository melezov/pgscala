package org.pgscala.converters

import org.joda.time.LocalDate;

/** Do not edit - generated in PGBuilder / PGLocalDateConverterBuilder.scala */

object PGLocalDateConverter extends PGConverter[LocalDate] {
  val PGType = PGNullableLocalDateConverter.pgType

  def toPGString(lD: LocalDate) =
    PGNullableConverter.toPGString(lD)

  def fromPGString(lD: String) =
    PGNullableConverter.fromPGString(lD)
}
