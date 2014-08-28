package org.pgscala.converters

/** Do not edit - generated in Builder / PGByteArrayConverterBuilder.scala */

object PGByteArrayConverter extends PGConverter[Array[Byte]] {
  val PGType = PGNullableByteArrayConverter.pgType

  def toPGString(bA: Array[Byte]) =
    PGNullableConverter.toPGString(bA)

  val defaultValue: Array[Byte] = Array.empty[Byte]

  def fromPGString(bA: String) =
    if (bA eq null) defaultValue else
    PGNullableConverter.fromPGString(bA)
}
