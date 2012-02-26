package hr.element.pgscala.converters

import scala.xml.Elem

/** Do not edit - generated in PGBuilder / PGElemConverterBuilder.scala */

object PGElemConverter extends PGConverter[Elem] {
  val PGType = PGNullableElemConverter.pgType

  def toPGString(e: Elem) =
    PGNullableConverter.toPGString(e)

  def fromPGString(e: String) =
    PGNullableConverter.fromPGString(e)
}
