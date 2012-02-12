package hr.element.pgscala.converters

import scala.xml.Elem

object PGNullableElemConverter extends PGTypeConverter[Option[Elem]] {
  def toPGString(value: Option[Elem]): String =
    value match {
      case Some(e) =>
        PGElemConverter.toPGString(e)
      case _ =>
        null
    }

  def fromPGString(value: String): Option[Elem] =
    if(value != null && value.nonEmpty) {
      Some(PGElemConverter.fromPGString(value))
    }
    else {
      None
    }

  override val PGType = java.sql.Types.SQLXML
}
