package org.pgscala.converters

/** Do not edit - generated in Builder / PGIntConverterBuilder.scala */

object PGOptionIntConverter extends PGConverter[Option[Int]] {
  val PGType = PGIntConverter.PGType

  def toPGString(oi: Option[Int]): String =
    oi match {
      case None =>
        null
      case Some(i) =>
        PGIntConverter.toPGString(i)
    }

  def fromPGString(i: String): Option[Int] =
    i match {
      case null | "" =>
        None
      case oi =>
        Some(PGIntConverter.fromPGString(oi))
    }
}
