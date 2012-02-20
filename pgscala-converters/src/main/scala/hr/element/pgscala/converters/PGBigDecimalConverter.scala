package hr.element.pgscala.converters

import java.math.BigDecimal

object PGBigDecimalConverter extends PGTypeConverter[BigDecimal] {
  val PGType = PGNullableBigDecimalConverter.pgType

  def toPGString(bD: BigDecimal): String =
    PGNullableConverter.toPGString(bD)

  def fromPGString(bD: String): BigDecimal =
    PGNullableConverter.fromPGString(bD)
}
