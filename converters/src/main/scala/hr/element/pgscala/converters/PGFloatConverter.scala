package hr.element.pgscala.converters

object PGFloatConverter extends PGTypeConverter[Float] {
  def toPGString(f: Float): String =
    f.toString

  def fromPGString(value: String): Float =
    java.lang.Float.parseFloat(value)

  override val PGType = java.sql.Types.FLOAT
}
