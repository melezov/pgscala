package hr.element.pgscala.converters

object PGBigDecimalConverter extends PGTypeConverter[BigDecimal] {
  val PGType = PGNullableBigDecimalConverter.pgType

  def toPGString(bD: BigDecimal): String =
    PGNullableConverter.toPGString(bD.bigDecimal)

  def fromPGString(bD: String): BigDecimal =
    PGNullableConverter.fromPGString(bD)
}
