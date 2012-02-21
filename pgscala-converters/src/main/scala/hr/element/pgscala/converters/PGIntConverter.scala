package hr.element.pgscala.converters

object PGIntConverter extends PGTypeConverter[Int] {
  val PGType = PGNullableIntegerConverter.pgType

  def toPGString(i: Int) =
    PGNullableConverter.toPGString(java.lang.Integer valueOf i)

  def fromPGString(i: String) =
    PGNullableConverter.fromPGString(i)
}
