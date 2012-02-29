package org.pgscala.converters

/** Do not edit - generated in PGBuilder / PGFloatConverterBuilder.scala */

object PGOptionFloatConverter extends PGConverter[Option[Float]] {
  val PGType = PGFloatConverter.PGType

  def toPGString(oF: Option[Float]): String =
    oF match {
      case None =>
        null
      case Some(f) =>
        PGFloatConverter.toPGString(f)
    }

  def fromPGString(f: String): Option[Float] =
    f match {
      case null | "" =>
        None
      case oF =>
        Some(PGFloatConverter.fromPGString(oF))
    }
}
