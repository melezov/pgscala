package hr.element.pgscala.converters

import org.joda.time.LocalDate

object PGNullableDateConverter extends PGTypeConverter[Option[LocalDate]] {
  def toPGString(value: Option[LocalDate]): String =
    value match {
      case Some(lD) =>
        PGDateConverter.toPGString(lD)
      case _ =>
        null
    }

  def fromPGString(value: String): Option[LocalDate] =
    if(value != null && value.nonEmpty) {
      Some(PGDateConverter.fromPGString(value))
    }
    else {
      None
    }

  override val PGType = java.sql.Types.DATE
}
