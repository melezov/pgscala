package org.pgscala.converters

import java.util.UUID

/** Do not edit - generated in PGBuilder / PGUUIDConverterBuilder.scala */

object PGUUIDConverter extends PGConverter[UUID] {
  val PGType = PGNullableUUIDConverter.pgType

  def toPGString(uuid: UUID) =
    PGNullableConverter.toPGString(uuid)

  def fromPGString(uuid: String) =
    PGNullableConverter.fromPGString(uuid)
}
