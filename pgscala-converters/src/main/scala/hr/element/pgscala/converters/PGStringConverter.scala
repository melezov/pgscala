package hr.element.pgscala.converters

object PGStringConverter extends PGTypeConverter[String] {
  def toPGString(s: String): String =
    s

  def fromPGString(value: String): String =
    value

  override val PGType = java.sql.Types.VARCHAR
}
