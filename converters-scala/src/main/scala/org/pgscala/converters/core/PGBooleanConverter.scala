package org.pgscala.converters

/** Do not edit - generated in Builder / PGBooleanConverterBuilder.scala */

object PGBooleanConverter extends PGConverter[Boolean] {
  val PGType = PGNullableBooleanConverter.pgType

  def toPGString(b: Boolean) =
    PGNullableConverter.toPGString(java.lang.Boolean valueOf b)

  val defaultValue: Boolean = false

  def fromPGString(b: String) =
    if (b eq null) defaultValue else
    PGNullableConverter.fromPGString(b)
}
