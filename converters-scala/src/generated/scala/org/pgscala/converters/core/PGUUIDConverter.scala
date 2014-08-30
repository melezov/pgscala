package org.pgscala.converters

import java.util.UUID

/** Do not edit - generated in Builder / PGUUIDConverterBuilder.scala */

object PGUUIDConverter extends PGConverter[UUID] {
  val PGType = PGNullableUUIDConverter.pgType

  def toPGString(uuid: UUID) =
    PGNullableUUIDConverter.uuidToString(uuid)

  val defaultValue: UUID = new UUID(0L, 0L)

  def fromPGString(uuid: String) =
    if (uuid eq null)
      defaultValue
    else
      PGNullableUUIDConverter.stringToUUID(uuid)
}
