package hr.element.pgscala.converters

object PGFloatConverter extends PGTypeConverter[Float] {
  def toString(f: Float): String =
    f.toString

  def fromString(value: String): Float =
    java.lang.Float.parseFloat(value)
}
