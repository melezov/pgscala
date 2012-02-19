package hr.element.pgscala.converters

import java.util.UUID

object PGUUIDConverter extends PGTypeConverter[UUID] {
  val PGType = PGNullableUUIDConverter.pgType

  def toPGString(uuid: UUID): String =
    PGNullableConverter.toPGString(uuid)

  def fromPGString(uuid: String): UUID =
    PGNullableConverter.fromPGString(uuid)
}
