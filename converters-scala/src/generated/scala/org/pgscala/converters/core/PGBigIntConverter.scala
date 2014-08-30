package org.pgscala.converters

/** Do not edit - generated in Builder / PGBigIntConverterBuilder.scala */

object PGBigIntConverter extends PGConverter[BigInt] {
  val PGType = PGNullableBigIntegerConverter.pgType

  def toPGString(bi: BigInt) =
    PGNullableBigIntegerConverter.bigIntegerToString(bi.bigInteger)

  val defaultValue: BigInt = BigInt(0)

  def fromPGString(bi: String) =
    if (bi eq null)
      defaultValue
    else
      new BigInt(PGNullableBigIntegerConverter.stringToBigInteger(bi))
}
