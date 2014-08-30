package org.pgscala.converters

/** Do not edit - generated in Builder / PGBigIntConverterBuilder.scala */

object PGOptionBigIntConverter extends PGConverter[Option[BigInt]] {
  val PGType = PGBigIntConverter.PGType

  def toPGString(obi: Option[BigInt]): String =
    obi match {
      case None =>
        null
      case Some(bi) =>
        PGBigIntConverter.toPGString(bi)
    }

  def fromPGString(bi: String): Option[BigInt] =
    bi match {
      case null | "" =>
        None
      case obi =>
        Some(PGBigIntConverter.fromPGString(obi))
    }
}
