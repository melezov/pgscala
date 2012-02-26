package hr.element.pgscala.converters

/** Do not edit - generated in PGBuilder / ShortConverterBuilder.scala */

object PGShortConverter extends PGTypeConverter[Short] {
  val PGType = PGNullableShortConverter.pgType

  def toPGString(s: Short) =
    PGNullableConverter.toPGString(java.lang.Short valueOf s)

  def fromPGString(s: String) =
    PGNullableConverter.fromPGString(s)
}
