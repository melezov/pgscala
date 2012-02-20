package hr.element.pgscala.converters

import java.math.BigInteger

object PGBigIntConverter extends PGTypeConverter[BigInt] {
  val PGType = PGNullableBigIntConverter.pgType

  def toPGString(bI: BigInt): String =
    PGNullableConverter.toPGString(bI)

  def fromPGString(bI: String): BigInt =
    PGNullableConverter.fromPGString(bI)
}
