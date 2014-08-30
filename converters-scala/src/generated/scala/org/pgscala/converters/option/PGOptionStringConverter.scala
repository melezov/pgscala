package org.pgscala.converters

/** Do not edit - generated in Builder / PGStringConverterBuilder.scala */

object PGOptionStringConverter extends PGConverter[Option[String]] {
  val PGType = PGStringConverter.PGType

  def toPGString(os: Option[String]): String =
    os match {
      case None =>
        null
      case Some(s) =>
        PGStringConverter.toPGString(s)
    }

  def fromPGString(s: String): Option[String] =
    s match {
      case null | "" =>
        None
      case os =>
        Some(PGStringConverter.fromPGString(os))
    }
}
