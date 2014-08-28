package org.pgscala.converters

/** Do not edit - generated in Builder / PGBigIntConverterBuilder.scala */

object PGBigIntConverter extends PGConverter[BigInt] {
  val PGType = PGNullableBigIntegerConverter.pgType

  def toPGString(bI: BigInt) =
    PGNullableConverter.toPGString(bI.bigInteger)

  val defaultValue: BigInt = BigInt(0)

  def fromPGString(bI: String) =
    if (bI eq null) defaultValue else
    new BigInt(PGNullableConverter.fromPGString(bI))
}
