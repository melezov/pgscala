package hr.element.pgscala.converters
package types

import org.joda.time.DateTime

object PGNullableDateTimeConverter extends PGTypeConverter[Option[DateTime]] {
  def toString(value: Option[DateTime]): String =
    value match {
      case Some(dT) =>
        PGDateTimeConverter.toString(dT)
      case _ =>
        null
    }

  def fromString(value: String): Option[DateTime] =
    if(value != null && value.nonEmpty) {
      Some(PGDateTimeConverter.fromString(value))
    }
    else {
      None
    }
}
