package hr.element.pgscala.converters

object PGIntegerConverter extends PGTypeConverter[Int] {
  def toPGString(i: Int): String =
    i.toString

  def fromPGString(value: String): Int =
    java.lang.Integer.parseInt(value)

  override val PGType = java.sql.Types.INTEGER
}
