package hr.element.pgscala.converters

import scala.xml.Elem

object PGElemConverter extends PGTypeConverter[Elem] {
  def toString(e: Elem): String =
    e.toString

  def fromString(value: String): Elem =
    scala.xml.XML.loadString(value)
}
