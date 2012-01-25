package hr.element.pgscala.converters

object PGShortConverter extends PGTypeConverter[Short] {
  def toPGString(s: Short): String =
    s.toString

  def fromPGString(value: String): Short =
    java.lang.Short.parseShort(value)
}
