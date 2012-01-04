package hr.element.pgscala.converters

object PGNullableShortConverter extends PGTypeConverter[Option[Short]] {
  def toString(value: Option[Short]): String =
    value match {
      case Some(s) =>
        PGShortConverter.toString(s)
      case _ =>
        null
    }

  def fromString(value: String): Option[Short] =
    if(value != null && value.nonEmpty) {
      Some(PGShortConverter.fromString(value))
    }
    else {
      None
    }
}
