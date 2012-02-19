package hr.element.pgscala.converters

import java.lang.Short

object PGShortConverter extends PGTypeConverter[Short] {
  val PGType = PGNullableShortConverter.pgType

  def toPGString(s: Short): String =
    PGNullableConverter.toPGString(s)

  def fromPGString(s: String): Short =
    PGNullableConverter.fromPGString(s)
}
