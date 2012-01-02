package hr.element.pgscala.util
package types

object PGNullableFloatConverter extends PGTypeConverter[Option[Float]] {
  def toString(value: Option[Float]): String =
    value match {
      case Some(f) =>
        PGFloatConverter.toString(f)
      case _ =>
        null
    }

  def fromString(value: String): Option[Float] =
    if(value != null && value.nonEmpty) {
      Some(PGFloatConverter.fromString(value))
    }
    else {
      None
    }
}
