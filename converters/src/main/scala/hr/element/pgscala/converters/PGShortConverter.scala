package hr.element.pgscala.converters

object PGShortConverter extends PGTypeConverter[Short] {
  def toString(s: Short): String =
    s.toString

  def fromString(value: String): Short =
    java.lang.Short.parseShort(value)
}
