package hr.element.pgscala.converters
package types

import java.util.UUID

object PGNullableUUIDConverter extends PGTypeConverter[Option[UUID]] {
  def toString(value: Option[UUID]): String =
    value match {
      case Some(u) =>
        PGUUIDConverter.toString(u)
      case _ =>
        null
    }

  def fromString(value: String): Option[UUID] =
    if(value != null && value.nonEmpty) {
      Some(PGUUIDConverter.fromString(value))
    }
    else {
      None
    }
}
