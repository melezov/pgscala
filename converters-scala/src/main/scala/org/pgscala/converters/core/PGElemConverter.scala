package org.pgscala.converters

import scala.xml.Elem

/** Do not edit - generated in Builder / PGElemConverterBuilder.scala */

object PGElemConverter extends PGConverter[Elem] {
  val PGType = PGNullableElemConverter.pgType

  def toPGString(e: Elem) =
    PGNullableConverter.toPGString(e)

  val defaultValue: Elem = null

  def fromPGString(e: String) =
    if (e eq null) defaultValue else
    PGNullableConverter.fromPGString(e)
}
