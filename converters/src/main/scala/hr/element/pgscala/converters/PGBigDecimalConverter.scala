package hr.element.pgscala.converters
package types

object PGBigDecimalConverter extends PGTypeConverter[BigDecimal] {
  def toString(bD: BigDecimal): String =
    bD.toString

  def fromString(value: String): BigDecimal =
    BigDecimal(value)
}
