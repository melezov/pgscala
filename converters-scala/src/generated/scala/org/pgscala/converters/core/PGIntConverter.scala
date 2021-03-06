package org.pgscala.converters

/** Do not edit - generated in Builder / PGIntConverterBuilder.scala */

object PGIntConverter extends PGConverter[Int] {
  val PGType = PGNullableIntegerConverter.pgType

  def toPGString(i: Int) =
    PGNullableIntegerConverter.integerToString(java.lang.Integer valueOf i)

  val defaultValue: Int = 0

  def fromPGString(i: String) =
    if (i eq null)
      defaultValue
    else
      PGNullableIntegerConverter.stringToInteger(i)
}
