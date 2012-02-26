package hr.element.pgscala.converters

/** Do not edit - generated in PGBuilder / PGLongConverterBuilder.scala */

object PGOptionLongConverter extends PGConverter[Option[Long]] {
  val PGType = PGLongConverter.PGType

  def toPGString(oL: Option[Long]): String =
    oL match {
      case None =>
        null
      case Some(l) =>
        PGLongConverter.toPGString(l)
    }

  def fromPGString(l: String): Option[Long] =
    l match {
      case null | "" =>
        None
      case oL =>
        Some(PGLongConverter.fromPGString(oL))
    }
}
