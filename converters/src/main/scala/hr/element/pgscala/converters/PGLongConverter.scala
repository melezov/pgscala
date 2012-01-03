package hr.element.pgscala.converters
package types

object PGLongConverter extends PGTypeConverter[Long] {
  def toString(l: Long): String =
    l.toString

  def fromString(value: String): Long =
    java.lang.Long.parseLong(value)
}
