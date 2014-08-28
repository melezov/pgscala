package org.pgscala.converters

/** Do not edit - generated in Builder / PGShortConverterBuilder.scala */

object PGShortConverter extends PGConverter[Short] {
  val PGType = PGNullableShortConverter.pgType

  def toPGString(s: Short) =
    PGNullableConverter.toPGString(java.lang.Short valueOf s)

  val defaultValue: Short = 0.toShort

  def fromPGString(s: String) =
    if (s eq null) defaultValue else
    PGNullableConverter.fromPGString(s)
}
