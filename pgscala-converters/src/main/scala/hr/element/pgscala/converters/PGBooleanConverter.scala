package hr.element.pgscala.converters

object PGBooleanConverter extends PGTypeConverter[Boolean] {
  def toPGString(b: Boolean): String =
    if (b) "t" else "f"

  def fromPGString(value: String): Boolean =
    value == "t"

  override val PGType = java.sql.Types.BIT
}
