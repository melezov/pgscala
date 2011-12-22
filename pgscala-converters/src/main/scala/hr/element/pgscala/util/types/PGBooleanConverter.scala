
package hr.element.pgscala.util
package types

object PGBooleanConverter extends PGTypeConverter[Boolean] {
  def toString(b: Boolean): String =
    b.toString()

  def fromString(value: String): Boolean =
    java.lang.Boolean.parseBoolean(value)
}
