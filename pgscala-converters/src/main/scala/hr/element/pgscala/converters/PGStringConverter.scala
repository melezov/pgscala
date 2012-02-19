package hr.element.pgscala.converters

import java.lang.String

object PGStringConverter extends PGTypeConverter[String] {
  val PGType = PGNullableStringConverter.pgType

  def toPGString(s: String): String = s

  def fromPGString(s: String): String = s
}
