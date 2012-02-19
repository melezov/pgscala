package hr.element.pgscala.converters

import java.lang.Long

object PGLongConverter extends PGTypeConverter[Long] {
  val PGType = PGNullableLongConverter.pgType

  def toPGString(l: Long): String =
    PGNullableConverter.toPGString(l)

  def fromPGString(l: String): Long =
    PGNullableConverter.fromPGString(l)
}
