package org.pgscala.converters

/** Do not edit - generated in Builder / PGMapConverterBuilder.scala */

object PGOptionMapConverter extends PGConverter[Option[Map[String, String]]] {
  val PGType = PGMapConverter.PGType

  def toPGString(om: Option[Map[String, String]]): String =
    om match {
      case None =>
        null
      case Some(m) =>
        PGMapConverter.toPGString(m)
    }

  def fromPGString(m: String): Option[Map[String, String]] =
    m match {
      case null | "" =>
        None
      case om =>
        Some(PGMapConverter.fromPGString(om))
    }
}
