package org.pgscala.converters

/** Do not edit - generated in Builder / PGByteArrayConverterBuilder.scala */

object PGOptionByteArrayConverter extends PGConverter[Option[Array[Byte]]] {
  val PGType = PGByteArrayConverter.PGType

  def toPGString(oba: Option[Array[Byte]]): String =
    oba match {
      case None =>
        null
      case Some(ba) =>
        PGByteArrayConverter.toPGString(ba)
    }

  def fromPGString(ba: String): Option[Array[Byte]] =
    ba match {
      case null | "" =>
        None
      case oba =>
        Some(PGByteArrayConverter.fromPGString(oba))
    }
}
