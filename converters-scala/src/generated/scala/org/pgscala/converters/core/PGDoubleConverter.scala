package org.pgscala.converters

/** Do not edit - generated in Builder / PGDoubleConverterBuilder.scala */

object PGDoubleConverter extends PGConverter[Double] {
  val PGType = PGNullableDoubleConverter.pgType

  def toPGString(d: Double) =
    PGNullableDoubleConverter.doubleToString(java.lang.Double valueOf d)

  val defaultValue: Double = 0.0

  def fromPGString(d: String) =
    if (d eq null)
      defaultValue
    else
      PGNullableDoubleConverter.stringToDouble(d)
}
