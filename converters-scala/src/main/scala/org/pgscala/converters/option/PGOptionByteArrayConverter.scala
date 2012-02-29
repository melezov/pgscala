package org.pgscala.converters

/** Do not edit - generated in PGBuilder / PGByteArrayConverterBuilder.scala */

object PGOptionByteArrayConverter extends PGConverter[Option[Array[Byte]]] {
  val PGType = PGByteArrayConverter.PGType

  def toPGString(oBA: Option[Array[Byte]]): String =
    oBA match {
      case None =>
        null
      case Some(bA) =>
        PGByteArrayConverter.toPGString(bA)
    }

  def fromPGString(bA: String): Option[Array[Byte]] =
    bA match {
      case null | "" =>
        None
      case oBA =>
        Some(PGByteArrayConverter.fromPGString(oBA))
    }
}
