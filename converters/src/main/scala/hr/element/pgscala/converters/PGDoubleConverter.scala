package hr.element.pgscala.converters
package types

object PGDoubleConverter extends PGTypeConverter[Double] {
  def toString(d: Double): String =
    d.toString

  def fromString(value: String): Double =
    java.lang.Double.parseDouble(value)
}
