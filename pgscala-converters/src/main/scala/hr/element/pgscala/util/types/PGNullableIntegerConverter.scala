package hr.element.pgscala.util
package types

object PGNullableIntegerConverter extends PGTypeConverter[Option[Int]] {
  def toString(value: Option[Int]): String =
    value match {
      case Some(i) =>
        PGIntegerConverter.toString(i)
      case _ =>
        null
    }

  def fromString(value: String): Option[Int] =
    if(value != null && value.nonEmpty) {
      Some(PGIntegerConverter.fromString(value))
    }
    else {
      None
    }
}
