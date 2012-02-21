package hr.element.pgscala.converters

import java.util.UUID

object PGUUIDConverter extends PGTypeConverter[UUID] {
  val PGType = PGNullableUUIDConverter.pgType

  def toPGString(uuid: UUID) =
    PGNullableConverter.toPGString(uuid)

  def fromPGString(uuid: String) =
    PGNullableConverter.fromPGString(uuid)
}
