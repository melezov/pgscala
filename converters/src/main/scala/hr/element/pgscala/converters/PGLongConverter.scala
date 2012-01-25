package hr.element.pgscala.converters

object PGLongConverter extends PGTypeConverter[Long] {
  def toPGString(l: Long): String =
    l.toString

  def fromPGString(value: String): Long =
    java.lang.Long.parseLong(value)
}
