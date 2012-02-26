package hr.element.pgscala.converters

/** Do not edit - generated in PGBuilder / BigIntConverterBuilder.scala */

object PGBigIntConverter extends PGTypeConverter[BigInt] {
  val PGType = PGNullableBigIntegerConverter.pgType

  def toPGString(bI: BigInt) =
    PGNullableConverter.toPGString(bI.bigInteger)

  def fromPGString(bI: String) =
    new BigInt(PGNullableConverter.fromPGString(bI))
}
