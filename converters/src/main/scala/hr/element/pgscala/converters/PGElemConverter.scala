package hr.element.pgscala.converters

import scala.xml.Elem

object PGElemConverter extends PGTypeConverter[Elem] {
  def toPGString(e: Elem): String =
    e.toString

  def fromPGString(value: String): Elem =
    scala.xml.XML.loadString(value)
}
