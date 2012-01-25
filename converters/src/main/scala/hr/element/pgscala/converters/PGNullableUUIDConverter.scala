package hr.element.pgscala.converters

import java.util.UUID

object PGNullableUUIDConverter extends PGTypeConverter[Option[UUID]] {
  def toPGString(value: Option[UUID]): String =
    value match {
      case Some(u) =>
        PGUUIDConverter.toPGString(u)
      case _ =>
        null
    }

  def fromPGString(value: String): Option[UUID] =
    if(value != null && value.nonEmpty) {
      Some(PGUUIDConverter.fromPGString(value))
    }
    else {
      None
    }
}
