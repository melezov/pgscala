package hr.element.pgscala.converters

/** Do not edit - generated in PGBuilder / IntConverterBuilder.scala */

object PGIntConverter extends PGTypeConverter[Int] {
  val PGType = PGNullableIntegerConverter.pgType

  def toPGString(i: Int) =
    PGNullableConverter.toPGString(java.lang.Integer valueOf i)

  def fromPGString(i: String) =
    PGNullableConverter.fromPGString(i)
}
