package hr.element.pgscala.converters

import org.joda.time.DateTime

object PGNullableDateTimeConverter extends PGTypeConverter[Option[DateTime]] {
  def toPGString(value: Option[DateTime]): String =
    value match {
      case Some(dT) =>
        PGDateTimeConverter.toPGString(dT)
      case _ =>
        null
    }

  def fromPGString(value: String): Option[DateTime] =
    if(value != null && value.nonEmpty) {
      Some(PGDateTimeConverter.fromPGString(value))
    }
    else {
      None
    }

  override val PGType = java.sql.Types.TIMESTAMP
}
