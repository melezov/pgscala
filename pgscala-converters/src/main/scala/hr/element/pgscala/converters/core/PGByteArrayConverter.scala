package hr.element.pgscala.converters

/** Do not edit - generated in PGBuilder / PGByteArrayConverterBuilder.scala */

object PGByteArrayConverter extends PGConverter[Array[Byte]] {
  val PGType = PGNullableByteArrayConverter.pgType

  def toPGString(bA: Array[Byte]) =
    PGNullableConverter.toPGString(bA)

  def fromPGString(bA: String) =
    PGNullableConverter.fromPGString(bA)
}
