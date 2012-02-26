package hr.element.pgscala.converters

/** Do not edit - generated in PGBuilder / PGDoubleConverterBuilder.scala */

object PGDoubleConverter extends PGConverter[Double] {
  val PGType = PGNullableDoubleConverter.pgType

  def toPGString(d: Double) =
    PGNullableConverter.toPGString(java.lang.Double valueOf d)

  def fromPGString(d: String) =
    PGNullableConverter.fromPGString(d)
}
