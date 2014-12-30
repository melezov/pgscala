package org.pgscala.converters

/** Do not edit - generated in Builder / PGBufferedImageConverterBuilder.scala */

object PGOptionBufferedImageConverter extends PGConverter[Option[BufferedImage]] {
  val PGType = PGBufferedImageConverter.PGType

  def toPGString(obi: Option[BufferedImage]): String =
    obi match {
      case None =>
        null
      case Some(bi) =>
        PGBufferedImageConverter.toPGString(bi)
    }

  def fromPGString(bi: String): Option[BufferedImage] =
    bi match {
      case null | "" =>
        None
      case obi =>
        Some(PGBufferedImageConverter.fromPGString(obi))
    }
}
