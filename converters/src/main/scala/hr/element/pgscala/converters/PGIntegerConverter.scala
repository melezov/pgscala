package hr.element.pgscala.converters

object PGIntegerConverter extends PGTypeConverter[Int] {
  def toString(i: Int): String =
    i.toString

  def fromString(value: String): Int =
    java.lang.Integer.parseInt(value)
}
