package hr.element.pgscala.converters

import java.math.BigInteger

object PGBigIntegerConverter extends PGTypeConverter[BigInteger] {
  val PGType = PGNullableBigIntegerConverter.pgType

  def toPGString(bI: BigInteger): String =
    PGNullableConverter.toPGString(bI)

  def fromPGString(bI: String): BigInteger =
    PGNullableConverter.fromPGString(bI)
}
