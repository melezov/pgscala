package org.pgscala.converters

/** Do not edit - generated in PGBuilder / PGIntConverterBuilder.scala */

object PGIntConverter extends PGConverter[Int] {
  val PGType = PGNullableIntegerConverter.pgType

  def toPGString(i: Int) =
    PGNullableConverter.toPGString(java.lang.Integer valueOf i)

  def fromPGString(i: String) =
    PGNullableConverter.fromPGString(i)
}
