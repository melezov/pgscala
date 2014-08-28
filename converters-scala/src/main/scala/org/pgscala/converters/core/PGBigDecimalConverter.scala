package org.pgscala.converters

/** Do not edit - generated in Builder / PGBigDecimalConverterBuilder.scala */

object PGBigDecimalConverter extends PGConverter[BigDecimal] {
  val PGType = PGNullableBigDecimalConverter.pgType

  def toPGString(bD: BigDecimal) =
    PGNullableConverter.toPGString(bD.bigDecimal)

  val defaultValue: BigDecimal = BigDecimal(0)

  def fromPGString(bD: String) =
    if (bD eq null) defaultValue else
    new BigDecimal(PGNullableConverter.fromPGString(bD))
}
