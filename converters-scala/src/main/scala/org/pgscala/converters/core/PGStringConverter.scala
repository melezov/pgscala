package org.pgscala.converters

/** Do not edit - generated in PGBuilder / PGStringConverterBuilder.scala */

object PGStringConverter extends PGConverter[String] {
  val PGType = PGNullableStringConverter.pgType

  def toPGString(s: String) = s

  def fromPGString(s: String) = s
}
