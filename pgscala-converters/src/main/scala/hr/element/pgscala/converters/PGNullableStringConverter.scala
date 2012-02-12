package hr.element.pgscala.converters

object PGNullableStringConverter extends PGTypeConverter[Option[String]] {
  def toPGString(value: Option[String]): String =
    value match {
      case Some(s) =>
        PGStringConverter.toPGString(s)
      case _ =>
        null
    }

  def fromPGString(value: String): Option[String] =
    if(value != null) {
      Some(PGStringConverter.fromPGString(value))
    }
    else {
      None
    }

  override val PGType = java.sql.Types.VARCHAR
}
