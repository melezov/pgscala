package hr.element.pgscala.converters

/** Do not edit - generated in PGBuilder / LongConverterBuilder.scala */

object PGLongConverter extends PGTypeConverter[Long] {
  val PGType = PGNullableLongConverter.pgType

  def toPGString(l: Long) =
    PGNullableConverter.toPGString(java.lang.Long valueOf l)

  def fromPGString(l: String) =
    PGNullableConverter.fromPGString(l)
}
