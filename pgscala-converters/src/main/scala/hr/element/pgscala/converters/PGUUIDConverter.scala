package hr.element.pgscala.converters

import java.util.UUID

object PGUUIDConverter extends PGTypeConverter[UUID] {
  def toPGString(u: UUID): String =
    u.toString

  def fromPGString(value: String): UUID =
    UUID.fromString(value)
}
