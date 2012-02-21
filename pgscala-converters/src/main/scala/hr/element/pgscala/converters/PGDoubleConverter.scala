package hr.element.pgscala.converters

object PGDoubleConverter extends PGTypeConverter[Double] {
  val PGType = PGNullableDoubleConverter.pgType

  def toPGString(d: Double) =
    PGNullableConverter.toPGString(java.lang.Double valueOf d)

  def fromPGString(d: String) =
    PGNullableConverter.fromPGString(d)
}
