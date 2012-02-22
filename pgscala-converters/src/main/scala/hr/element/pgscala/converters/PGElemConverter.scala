package hr.element.pgscala.converters

import scala.xml.Elem

object PGElemConverter extends PGTypeConverter[Elem] {
  val PGType = PGNullableElemConverter.pgType

  def toPGString(e: Elem) =
    PGNullableConverter.toPGString(e)

  def fromPGString(e: String) =
    PGNullableConverter.fromPGString(e)
}
