package hr.element.pgscala.converters

object PGNullableBigDecimalConverter extends PGTypeConverter[Option[BigDecimal]] {
  def toPGString(value: Option[BigDecimal]): String =
    value match {
      case Some(bD) =>
        PGBigDecimalConverter.toPGString(bD)
      case _ =>
        null
    }

  def fromPGString(value: String): Option[BigDecimal] =
    if(value != null && value.nonEmpty) {
      Some(PGBigDecimalConverter.fromPGString(value))
    }
    else {
      None
    }

  override val PGType = java.sql.Types.NUMERIC
}
