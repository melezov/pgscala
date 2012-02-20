package hr.element.pgscala.converters

object PGLongConverter extends PGTypeConverter[Long] {
  val PGType = PGNullableLongConverter.pgType

  def toPGString(l: Long): String =
    PGNullableConverter.toPGString(l)

  def fromPGString(l: String): Long =
    PGNullableConverter.fromPGString(l)
}
