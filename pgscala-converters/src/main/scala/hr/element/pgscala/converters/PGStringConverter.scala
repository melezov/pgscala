package hr.element.pgscala.converters

/** Do not edit - generated in PGBuilder / StringConverterBuilder.scala */

object PGStringConverter extends PGTypeConverter[String] {
  val PGType = PGNullableStringConverter.pgType

  def toPGString(s: String) = s

  def fromPGString(s: String) = s
}
