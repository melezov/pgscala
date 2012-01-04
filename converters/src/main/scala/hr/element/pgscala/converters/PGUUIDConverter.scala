package hr.element.pgscala.converters

import java.util.UUID

object PGUUIDConverter extends PGTypeConverter[UUID] {
  def toString(u: UUID): String =
    u.toString

  def fromString(value: String): UUID =
    UUID.fromString(value)
}
