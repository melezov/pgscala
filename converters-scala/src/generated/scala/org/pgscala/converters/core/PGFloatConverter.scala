package org.pgscala.converters

/** Do not edit - generated in Builder / PGFloatConverterBuilder.scala */

object PGFloatConverter extends PGConverter[Float] {
  val PGType = PGNullableFloatConverter.pgType

  def toPGString(f: Float) =
    PGNullableFloatConverter.floatToString(java.lang.Float valueOf f)

  val defaultValue: Float = 0.0f

  def fromPGString(f: String) =
    if (f eq null)
      defaultValue
    else
      PGNullableFloatConverter.stringToFloat(f)
}
