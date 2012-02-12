package hr.element.pgscala.converters

object PGNullableLongConverter extends PGTypeConverter[Option[Long]] {
  def toPGString(value: Option[Long]): String =
    value match {
      case Some(l) =>
        PGLongConverter.toPGString(l)
      case _ =>
        null
    }

  def fromPGString(value: String): Option[Long] =
    if(value != null && value.nonEmpty) {
      Some(PGLongConverter.fromPGString(value))
    }
    else {
      None
    }

  override val PGType = java.sql.Types.BIGINT
}
