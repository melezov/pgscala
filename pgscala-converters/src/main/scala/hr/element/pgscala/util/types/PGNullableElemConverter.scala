package hr.element.pgscala.util
package types

import scala.xml.Elem

object PGNullableElemConverter extends PGTypeConverter[Option[Elem]] {
  def toString(value: Option[Elem]): String =
    value match {
      case Some(e) =>
        PGElemConverter.toString(e)
      case _ =>
        null
    }

  def fromString(value: String): Option[Elem] =
    if(value != null && value.nonEmpty) {
      Some(PGElemConverter.fromString(value))
    }
    else {
      None
    }
}
