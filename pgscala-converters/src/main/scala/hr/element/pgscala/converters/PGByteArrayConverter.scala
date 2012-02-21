package hr.element.pgscala.converters

object PGByteArrayConverter extends PGTypeConverter[Array[Byte]] {
  val PGType = PGNullableByteArrayConverter.pgType

  def toPGString(bA: Array[Byte]) =
    PGNullableConverter.toPGString(bA)

  def fromPGString(bA: String) =
    PGNullableConverter.fromPGString(bA)
}
