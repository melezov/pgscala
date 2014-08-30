package org.pgscala.converters

/** Do not edit - generated in Builder / PGByteArrayConverterBuilder.scala */

object PGByteArrayConverter extends PGConverter[Array[Byte]] {
  val PGType = PGNullableByteArrayConverter.pgType

  def toPGString(ba: Array[Byte]) =
    PGNullableByteArrayConverter.byteArrayToString(ba)

  val defaultValue: Array[Byte] = Array.empty[Byte]

  def fromPGString(ba: String) =
    if (ba eq null)
      defaultValue
    else
      PGNullableByteArrayConverter.stringToByteArray(ba)
}
