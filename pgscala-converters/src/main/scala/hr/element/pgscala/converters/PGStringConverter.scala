package hr.element.pgscala.converters

object PGStringConverter extends PGTypeConverter[String] {
  val PGType = PGNullableStringConverter.pgType

  def toPGString(s: String): String = s

  def fromPGString(s: String): String = s
}
