package hr.element.pgscala.converters

object PGBigDecimalConverter extends PGTypeConverter[BigDecimal] {
  def toPGString(bD: BigDecimal): String =
    bD.toString

  def fromPGString(value: String): BigDecimal =
    BigDecimal(value)

  override val PGType = java.sql.Types.NUMERIC
}
