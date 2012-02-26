package hr.element.pgscala.converters

/** Do not edit - generated in PGBuilder / PGBigDecimalConverterBuilder.scala */

object PGBigDecimalConverter extends PGConverter[BigDecimal] {
  val PGType = PGNullableBigDecimalConverter.pgType

  def toPGString(bD: BigDecimal) =
    PGNullableConverter.toPGString(bD.bigDecimal)

  def fromPGString(bD: String) =
    new BigDecimal(PGNullableConverter.fromPGString(bD))
}
