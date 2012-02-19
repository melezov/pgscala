package hr.element.pgscala.converters

object PGByteArrayConverter extends PGTypeConverter[Array[Byte]] {
  val PGType = PGNullableByteArrayConverter.pgType

  def toPGString(bA: Array[Byte]): String =
    PGNullableConverter.toPGString(bA)

  def fromPGString(bA: String): Array[Byte] =
    PGNullableConverter.fromPGString(bA)
}
