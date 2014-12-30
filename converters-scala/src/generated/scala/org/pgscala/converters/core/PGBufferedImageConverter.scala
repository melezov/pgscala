package org.pgscala.converters

/** Do not edit - generated in Builder / PGBufferedImageConverterBuilder.scala */

object PGBufferedImageConverter extends PGConverter[BufferedImage] {
  val PGType = PGNullableBufferedImageConverter.pgType

  def toPGString(bi: BufferedImage) =
    PGNullableBufferedImageConverter.bufferedImageToString(bi)

  val defaultValue: BufferedImage = new java.awt.image.BufferedImage(1, 1, java.awt.image.BufferedImage.TYPE_4BYTE_ABGR)

  def fromPGString(bi: String) =
    if (bi eq null)
      defaultValue
    else
      PGNullableBufferedImageConverter.stringToBufferedImage(bi)
}
