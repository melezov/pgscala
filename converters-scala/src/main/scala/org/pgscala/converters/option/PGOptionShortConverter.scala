package org.pgscala.converters

/** Do not edit - generated in PGBuilder / PGShortConverterBuilder.scala */

object PGOptionShortConverter extends PGConverter[Option[Short]] {
  val PGType = PGShortConverter.PGType

  def toPGString(oS: Option[Short]): String =
    oS match {
      case None =>
        null
      case Some(s) =>
        PGShortConverter.toPGString(s)
    }

  def fromPGString(s: String): Option[Short] =
    s match {
      case null | "" =>
        None
      case oS =>
        Some(PGShortConverter.fromPGString(oS))
    }
}
