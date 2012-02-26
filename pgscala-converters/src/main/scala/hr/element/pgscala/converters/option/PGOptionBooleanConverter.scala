package hr.element.pgscala.converters

/** Do not edit - generated in PGBuilder / PGBooleanConverterBuilder.scala */

object PGOptionBooleanConverter extends PGConverter[Option[Boolean]] {
  val PGType = PGBooleanConverter.PGType

  def toPGString(oB: Option[Boolean]): String =
    oB match {
      case None =>
        null
      case Some(b) =>
        PGBooleanConverter.toPGString(b)
    }

  def fromPGString(b: String): Option[Boolean] =
    b match {
      case null | "" =>
        None
      case oB =>
        Some(PGBooleanConverter.fromPGString(oB))
    }
}
