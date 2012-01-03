package hr.element.pgscala.util
package types

object PGNullableLongConverter extends PGTypeConverter[Option[Long]] {
  def toString(value: Option[Long]): String =
    value match {
      case Some(l) =>
        PGLongConverter.toString(l)
      case _ =>
        null
    }

  def fromString(value: String): Option[Long] =
    if(value != null && value.nonEmpty) {
      Some(PGLongConverter.fromString(value))
    }
    else {
      None
    }
}
