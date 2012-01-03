package hr.element.pgscala.util
package types

object PGStringConverter extends PGTypeConverter[String] {
  def toString(s: String): String =
    s

  def fromString(value: String): String =
    value
}
