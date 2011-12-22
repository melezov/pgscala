
package hr.element.pgscala.util
package types

object PGIntegerConverter extends PGTypeConverter[Int] {
  def toString(i: Int): String =
    i.toString()

  def fromString(value: String): Int =
    java.lang.Integer.parseInt(value)
}
