package hr.element.pgscala.converters
package types

object PGNullableStringConverter extends PGTypeConverter[Option[String]] {
  def toString(value: Option[String]): String =
    value match {
      case Some(s) =>
        PGStringConverter.toString(s)
      case _ =>
        null
    }

  def fromString(value: String): Option[String] =
    if(value != null) {
      Some(PGStringConverter.fromString(value))
    }
    else {
      None
    }
}
