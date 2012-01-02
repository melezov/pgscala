package hr.element.pgscala.util
package types

object PGFloatConverter extends PGTypeConverter[Float] {
  def toString(f: Float): String =
    f.toString

  def fromString(value: String): Float =
    java.lang.Float.parseFloat(value)
}
