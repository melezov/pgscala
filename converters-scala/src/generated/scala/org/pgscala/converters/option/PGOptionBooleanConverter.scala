package org.pgscala.converters

/** Do not edit - generated in Builder / PGBooleanConverterBuilder.scala */

object PGOptionBooleanConverter extends PGConverter[Option[Boolean]] {
  val PGType = PGBooleanConverter.PGType

  def toPGString(ob: Option[Boolean]): String =
    ob match {
      case None =>
        null
      case Some(b) =>
        PGBooleanConverter.toPGString(b)
    }

  def fromPGString(b: String): Option[Boolean] =
    b match {
      case null | "" =>
        None
      case ob =>
        Some(PGBooleanConverter.fromPGString(ob))
    }
}
