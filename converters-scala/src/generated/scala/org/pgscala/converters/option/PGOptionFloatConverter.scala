package org.pgscala.converters

/** Do not edit - generated in Builder / PGFloatConverterBuilder.scala */

object PGOptionFloatConverter extends PGConverter[Option[Float]] {
  val PGType = PGFloatConverter.PGType

  def toPGString(of: Option[Float]): String =
    of match {
      case None =>
        null
      case Some(f) =>
        PGFloatConverter.toPGString(f)
    }

  def fromPGString(f: String): Option[Float] =
    f match {
      case null | "" =>
        None
      case of =>
        Some(PGFloatConverter.fromPGString(of))
    }
}
