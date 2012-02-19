package hr.element.pgscala.converters

import java.lang.Integer

object PGIntegerConverter extends PGTypeConverter[Integer] {
  val PGType = PGNullableIntegerConverter.pgType

  def toPGString(i: Integer): String =
    PGNullableConverter.toPGString(i)

  def fromPGString(i: String): Integer =
    PGNullableConverter.fromPGString(i)
}
