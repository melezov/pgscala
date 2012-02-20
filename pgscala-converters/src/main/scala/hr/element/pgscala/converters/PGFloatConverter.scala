package hr.element.pgscala.converters

object PGFloatConverter extends PGTypeConverter[Float] {
  val PGType = PGNullableFloatConverter.pgType

  def toPGString(f: Float): String =
    PGNullableConverter.toPGString(f)

  def fromPGString(f: String): Float =
    PGNullableConverter.fromPGString(f)
}
