package hr.element.pgscala.converters

import java.lang.Integer

object PGIntConverter extends PGTypeConverter[Int] {
  val PGType = PGNullableIntConverter.pgType

  def toPGString(i: Int): String =
    PGNullableConverter.toPGString(i)

  def fromPGString(i: String): Int =
    PGNullableConverter.fromPGString(i)
}
