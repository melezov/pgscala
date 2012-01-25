package hr.element.pgscala.converters

object PGNullableFloatConverter extends PGTypeConverter[Option[Float]] {
  def toPGString(value: Option[Float]): String =
    value match {
      case Some(f) =>
        PGFloatConverter.toPGString(f)
      case _ =>
        null
    }

  def fromPGString(value: String): Option[Float] =
    if(value != null && value.nonEmpty) {
      Some(PGFloatConverter.fromPGString(value))
    }
    else {
      None
    }
}
