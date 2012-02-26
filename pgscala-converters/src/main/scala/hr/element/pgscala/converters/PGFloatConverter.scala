package hr.element.pgscala.converters

/** Do not edit - generated in PGBuilder / PGFloatConverterBuilder.scala */

object PGFloatConverter extends PGConverter[Float] {
  val PGType = PGNullableFloatConverter.pgType

  def toPGString(f: Float) =
    PGNullableConverter.toPGString(java.lang.Float valueOf f)

  def fromPGString(f: String) =
    PGNullableConverter.fromPGString(f)
}
