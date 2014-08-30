package org.pgscala.converters

/** Do not edit - generated in Builder / PGDoubleConverterBuilder.scala */

object PGOptionDoubleConverter extends PGConverter[Option[Double]] {
  val PGType = PGDoubleConverter.PGType

  def toPGString(od: Option[Double]): String =
    od match {
      case None =>
        null
      case Some(d) =>
        PGDoubleConverter.toPGString(d)
    }

  def fromPGString(d: String): Option[Double] =
    d match {
      case null | "" =>
        None
      case od =>
        Some(PGDoubleConverter.fromPGString(od))
    }
}
