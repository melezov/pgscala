package org.pgscala.converters

import java.util.UUID

/** Do not edit - generated in Builder / PGUUIDConverterBuilder.scala */

object PGUUIDConverter extends PGConverter[UUID] {
  val PGType = PGNullableUUIDConverter.pgType

  def toPGString(uuid: UUID) =
    PGNullableConverter.toPGString(uuid)

  val defaultValue: UUID = UUID.fromString("0-0-0-0-0")

  def fromPGString(uuid: String) =
    if (uuid eq null) defaultValue else
    PGNullableConverter.fromPGString(uuid)
}
