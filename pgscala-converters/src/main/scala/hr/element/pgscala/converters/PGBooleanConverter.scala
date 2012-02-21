package hr.element.pgscala.converters

object PGBooleanConverter extends PGTypeConverter[Boolean] {
  val PGType = PGNullableBooleanConverter.pgType

  def toPGString(b: Boolean) =
    PGNullableConverter.toPGString(java.lang.Boolean valueOf b)

  def fromPGString(b: String) =
    PGNullableConverter.fromPGString(b)
}
