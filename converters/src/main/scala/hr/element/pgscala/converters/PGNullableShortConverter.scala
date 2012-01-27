package hr.element.pgscala.converters

object PGNullableShortConverter extends PGTypeConverter[Option[Short]] {
  def toPGString(value: Option[Short]): String =
    value match {
      case Some(s) =>
        PGShortConverter.toPGString(s)
      case _ =>
        null
    }

  def fromPGString(value: String): Option[Short] =
    if(value != null && value.nonEmpty) {
      Some(PGShortConverter.fromPGString(value))
    }
    else {
      None
    }

  override val PGType = java.sql.Types.SMALLINT
}
