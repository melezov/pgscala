package hr.element.pgscala.converters

object PGDoubleConverter extends PGTypeConverter[Double] {
  def toPGString(d: Double): String =
    d.toString

  def fromPGString(value: String): Double =
    java.lang.Double.parseDouble(value)

  override val PGType = java.sql.Types.DOUBLE
}
