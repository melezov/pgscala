package org.pgscala.converters

/** Do not edit - generated in PGBuilder / PGShortConverterBuilder.scala */

object PGShortConverter extends PGConverter[Short] {
  val PGType = PGNullableShortConverter.pgType

  def toPGString(s: Short) =
    PGNullableConverter.toPGString(java.lang.Short valueOf s)

  def fromPGString(s: String) =
    PGNullableConverter.fromPGString(s)
}
