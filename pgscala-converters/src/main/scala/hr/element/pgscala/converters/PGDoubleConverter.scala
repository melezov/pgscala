package hr.element.pgscala.converters

import java.lang.Double

object PGDoubleConverter extends PGTypeConverter[Double] {
  val PGType = PGNullableDoubleConverter.pgType

  def toPGString(d: Double): String =
    PGNullableConverter.toPGString(d)

  def fromPGString(d: String): Double =
    PGNullableConverter.fromPGString(d)
}
