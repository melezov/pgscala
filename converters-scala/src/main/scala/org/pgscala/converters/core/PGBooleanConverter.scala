package org.pgscala.converters

/** Do not edit - generated in Builder / PGBooleanConverterBuilder.scala */

object PGBooleanConverter extends PGConverter[Boolean] {
  val PGType = PGNullableBooleanConverter.pgType

  def toPGString(b: Boolean) =
    PGNullableConverter.toPGString(java.lang.Boolean valueOf b)

  def fromPGString(b: String) =
    PGNullableConverter.fromPGString(b)
}
