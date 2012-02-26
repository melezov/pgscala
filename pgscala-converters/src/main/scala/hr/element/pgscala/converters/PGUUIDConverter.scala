package hr.element.pgscala.converters

import java.util.UUID

/** Do not edit - generated in PGBuilder / UUIDConverterBuilder.scala */

object PGUUIDConverter extends PGTypeConverter[UUID] {
  val PGType = PGNullableUUIDConverter.pgType

  def toPGString(uuid: UUID) =
    PGNullableConverter.toPGString(uuid)

  def fromPGString(uuid: String) =
    PGNullableConverter.fromPGString(uuid)
}
