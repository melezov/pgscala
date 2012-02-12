package hr.element.pgscala.converters

object PGNullableDoubleConverter extends PGTypeConverter[Option[Double]] {
  def toPGString(value: Option[Double]): String =
    value match {
      case Some(d) =>
        PGDoubleConverter.toPGString(d)
      case _ =>
        null
    }

  def fromPGString(value: String): Option[Double] =
    if(value != null && value.nonEmpty) {
      Some(PGDoubleConverter.fromPGString(value))
    }
    else {
      None
    }

  override val PGType = java.sql.Types.DOUBLE
}
