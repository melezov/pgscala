package org.pgscala.converters

/** Do not edit - generated in Builder / PGIntConverterBuilder.scala */

object PGIntConverter extends PGConverter[Int] {
  val PGType = PGNullableIntegerConverter.pgType

  def toPGString(i: Int) =
    PGNullableConverter.toPGString(java.lang.Integer valueOf i)

  val defaultValue: Int = 0

  def fromPGString(i: String) =
    if (i eq null) defaultValue else
    PGNullableConverter.fromPGString(i)
}
