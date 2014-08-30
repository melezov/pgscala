package org.pgscala.converters

/** Do not edit - generated in Builder / PGBooleanConverterBuilder.scala */

object PGBooleanConverter extends PGConverter[Boolean] {
  val PGType = PGNullableBooleanConverter.pgType

  def toPGString(b: Boolean) =
    PGNullableBooleanConverter.booleanToString(java.lang.Boolean valueOf b)

  val defaultValue: Boolean = false

  def fromPGString(b: String) =
    if (b eq null)
      defaultValue
    else
      PGNullableBooleanConverter.stringToBoolean(b)
}
