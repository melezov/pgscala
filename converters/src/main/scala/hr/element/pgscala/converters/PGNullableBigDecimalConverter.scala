package hr.element.pgscala.converters
package types

object PGNullableBigDecimalConverter extends PGTypeConverter[Option[BigDecimal]] {
  def toString(value: Option[BigDecimal]): String =
    value match {
      case Some(bD) =>
        PGBigDecimalConverter.toString(bD)
      case _ =>
        null
    }

  def fromString(value: String): Option[BigDecimal] =
    if(value != null && value.nonEmpty) {
      Some(PGBigDecimalConverter.fromString(value))
    }
    else {
      None
    }
}
