package org.pgscala.converters
import scala.xml.Elem
/** Do not edit - generated in Builder / PGElemConverterBuilder.scala */

object PGElemConverter extends PGConverter[Elem] {
  val PGType = PGNullableElemConverter.pgType

  def toPGString(e: Elem) =
    PGNullableElemConverter.elemToString(e)

  val defaultValue: Elem = null // no sane default for XML

  def fromPGString(e: String) =
    if (e eq null)
      defaultValue
    else
      PGNullableElemConverter.stringToElem(e)
}
