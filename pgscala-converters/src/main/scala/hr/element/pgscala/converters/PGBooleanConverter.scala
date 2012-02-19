package hr.element.pgscala.converters

import java.lang.Boolean

object PGBooleanConverter extends PGTypeConverter[Boolean] {
  val PGType = PGNullableBooleanConverter.pgType

  def toPGString(b: Boolean): String =
    PGNullableConverter.toPGString(b)

  def fromPGString(b: String): Boolean =
    PGNullableConverter.fromPGString(b)
}
