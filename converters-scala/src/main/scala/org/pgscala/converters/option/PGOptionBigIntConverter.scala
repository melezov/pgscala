package org.pgscala.converters

/** Do not edit - generated in PGBuilder / PGBigIntConverterBuilder.scala */

object PGOptionBigIntConverter extends PGConverter[Option[BigInt]] {
  val PGType = PGBigIntConverter.PGType

  def toPGString(oBI: Option[BigInt]): String =
    oBI match {
      case None =>
        null
      case Some(bI) =>
        PGBigIntConverter.toPGString(bI)
    }

  def fromPGString(bI: String): Option[BigInt] =
    bI match {
      case null | "" =>
        None
      case oBI =>
        Some(PGBigIntConverter.fromPGString(oBI))
    }
}
