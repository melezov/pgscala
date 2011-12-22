
package hr.element.pgscala.util
package types

object PGShortConverter extends PGTypeConverter[Short] {
  def toString(s: Short): String =
    s.toString()

  def fromString(value: String): Short =
    java.lang.Short.parseShort(value)
}
