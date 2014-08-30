package org.pgscala.converters

/** Do not edit - generated in Builder / PGBigDecimalConverterBuilder.scala */

object PGBigDecimalConverter extends PGConverter[BigDecimal] {
  val PGType = PGNullableBigDecimalConverter.pgType

  def toPGString(bd: BigDecimal) =
    PGNullableBigDecimalConverter.bigDecimalToString(bd.bigDecimal)

  val defaultValue: BigDecimal = BigDecimal(0)

  def fromPGString(bd: String) =
    if (bd eq null)
      defaultValue
    else
      BigDecimal(PGNullableBigDecimalConverter.stringToBigDecimal(bd))
}
