package hr.element.pgscala.converters

object PGBooleanConverter extends PGTypeConverter[Boolean] {
  def toString(b: Boolean): String =
    if (b) "t" else "f"

  def fromString(value: String): Boolean =
    value == "t"
}
