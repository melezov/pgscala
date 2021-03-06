package org.pgscala.converters

/** Do not edit - generated in Builder / PGMapConverterBuilder.scala */

object PGMapConverter extends PGConverter[Map[String, String]] {
  val PGType = PGNullableMapConverter.pgType

  def toPGString(m: Map[String, String]) =
    PGNullableMapConverter.mapToString(m)

  val defaultValue: Map[String, String] = Map.empty[String, String]

  def fromPGString(m: String) =
    if (m eq null)
      defaultValue
    else
      PGNullableMapConverter.stringToMap(m)
}
