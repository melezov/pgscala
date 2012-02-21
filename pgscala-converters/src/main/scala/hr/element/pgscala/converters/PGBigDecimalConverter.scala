package hr.element.pgscala.converters

object PGBigDecimalConverter extends PGTypeConverter[BigDecimal] {
  val PGType = PGNullableBigDecimalConverter.pgType

  def toPGString(bD: BigDecimal) =
    PGNullableConverter.toPGString(bD.bigDecimal)

  def fromPGString(bD: String) =
    new BigDecimal(PGNullableConverter.fromPGString(bD))
}
