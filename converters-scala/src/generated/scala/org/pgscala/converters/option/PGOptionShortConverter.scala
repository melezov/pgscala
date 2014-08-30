package org.pgscala.converters

/** Do not edit - generated in Builder / PGShortConverterBuilder.scala */

object PGOptionShortConverter extends PGConverter[Option[Short]] {
  val PGType = PGShortConverter.PGType

  def toPGString(os: Option[Short]): String =
    os match {
      case None =>
        null
      case Some(s) =>
        PGShortConverter.toPGString(s)
    }

  def fromPGString(s: String): Option[Short] =
    s match {
      case null | "" =>
        None
      case os =>
        Some(PGShortConverter.fromPGString(os))
    }
}
