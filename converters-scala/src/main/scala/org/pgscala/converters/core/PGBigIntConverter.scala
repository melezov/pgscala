package org.pgscala.converters

/** Do not edit - generated in PGBuilder / PGBigIntConverterBuilder.scala */

object PGBigIntConverter extends PGConverter[BigInt] {
  val PGType = PGNullableBigIntegerConverter.pgType

  def toPGString(bI: BigInt) =
    PGNullableConverter.toPGString(bI.bigInteger)

  def fromPGString(bI: String) =
    new BigInt(PGNullableConverter.fromPGString(bI))
}
