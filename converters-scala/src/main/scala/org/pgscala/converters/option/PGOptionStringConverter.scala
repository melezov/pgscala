package org.pgscala.converters

/** Do not edit - generated in Builder / PGStringConverterBuilder.scala */

object PGOptionStringConverter extends PGConverter[Option[String]] {
  val PGType = PGStringConverter.PGType

  def toPGString(oS: Option[String]): String =
    oS match {
      case None =>
        null
      case Some(s) =>
        PGStringConverter.toPGString(s)
    }

  def fromPGString(s: String): Option[String] =
    s match {
      case null | "" =>
        None
      case oS =>
        Some(PGStringConverter.fromPGString(oS))
    }
}
