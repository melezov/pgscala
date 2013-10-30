package org.pgscala.converters

/** Do not edit - generated in Builder / PGMapConverterBuilder.scala */

object PGMapConverter extends PGConverter[Map[String, String]] {
  val PGType = PGNullableMapConverter.pgType

  def toPGString(m: Map[String, String]) =
    PGNullableConverter.toPGString(m)

  def fromPGString(m: String) =
    PGNullableConverter.fromPGString(m)
}
