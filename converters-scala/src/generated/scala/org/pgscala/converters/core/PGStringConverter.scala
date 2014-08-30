package org.pgscala.converters

/** Do not edit - generated in Builder / PGStringConverterBuilder.scala */

object PGStringConverter extends PGConverter[String] {
  val PGType = PGNullableStringConverter.pgType

  def toPGString(s: String) = s

  val defaultValue: String = ""

  def fromPGString(s: String) =
    if (s eq null)
      defaultValue
    else
      s
}
