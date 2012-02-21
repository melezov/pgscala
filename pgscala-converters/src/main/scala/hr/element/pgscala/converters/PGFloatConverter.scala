package hr.element.pgscala.converters

object PGFloatConverter extends PGTypeConverter[Float] {
  val PGType = PGNullableFloatConverter.pgType

  def toPGString(f: Float) =
    PGNullableConverter.toPGString(java.lang.Float valueOf f)

  def fromPGString(f: String) =
    PGNullableConverter.fromPGString(f)
}
