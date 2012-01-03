package hr.element.pgscala.converters
package types

import org.joda.time.LocalDate

object PGNullableDateConverter extends PGTypeConverter[Option[LocalDate]] {
  def toString(value: Option[LocalDate]): String =
    value match {
      case Some(lD) =>
        PGDateConverter.toString(lD)
      case _ =>
        null
    }

  def fromString(value: String): Option[LocalDate] =
    if(value != null && value.nonEmpty) {
      Some(PGDateConverter.fromString(value))
    }
    else {
      None
    }
}
