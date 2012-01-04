package hr.element.pgscala.converters

object PGNullableBooleanConverter extends PGTypeConverter[Option[Boolean]] {
  def toString(value: Option[Boolean]): String =
    value match {
      case Some(b) =>
        PGBooleanConverter.toString(b)
      case _ =>
        null
    }

  def fromString(value: String): Option[Boolean] =
    if(value != null && value.nonEmpty) {
      Some(PGBooleanConverter.fromString(value))
    }
    else {
      None
    }
}
