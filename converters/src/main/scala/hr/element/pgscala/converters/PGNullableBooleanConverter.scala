package hr.element.pgscala.converters

object PGNullableBooleanConverter extends PGTypeConverter[Option[Boolean]] {
  def toPGString(value: Option[Boolean]): String =
    value match {
      case Some(b) =>
        PGBooleanConverter.toPGString(b)
      case _ =>
        null
    }

  def fromPGString(value: String): Option[Boolean] =
    if(value != null && value.nonEmpty) {
      Some(PGBooleanConverter.fromPGString(value))
    }
    else {
      None
    }
}
