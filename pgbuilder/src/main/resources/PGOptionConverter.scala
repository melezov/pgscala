package hr.element.pgscala.converters

/** Do not edit - generated in PGBuilder / { builder } */

object PGOption{ fileName }Converter extends PGTypeConverter[Option[{ javaType }]] {
  val PGType = "{ pgType }"

  def toPGString(value: Option[Short]): String =
    value match {
      case Some(s) =>
        PGShortConverter.toPGString(s)
      case _ =>
        null
    }

  def fromPGString(value: String): Option[Short] =
    if(value != null && value.nonEmpty) {
      Some(PGShortConverter.fromPGString(value))
    }
    else {
      None
    }
}
