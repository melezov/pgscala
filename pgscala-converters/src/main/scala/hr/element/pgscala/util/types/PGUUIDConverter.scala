package hr.element.pgscala.util
package types

import java.util.UUID

object PGUUIDConverter extends PGTypeConverter[UUID] {
  def toString(u: UUID): String =
    u.toString

  def fromString(value: String): UUID =
    UUID.fromString(value)
}
