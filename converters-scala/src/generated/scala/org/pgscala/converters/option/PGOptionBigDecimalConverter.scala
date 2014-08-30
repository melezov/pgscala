package org.pgscala.converters

/** Do not edit - generated in Builder / PGBigDecimalConverterBuilder.scala */

object PGOptionBigDecimalConverter extends PGConverter[Option[BigDecimal]] {
  val PGType = PGBigDecimalConverter.PGType

  def toPGString(obd: Option[BigDecimal]): String =
    obd match {
      case None =>
        null
      case Some(bd) =>
        PGBigDecimalConverter.toPGString(bd)
    }

  def fromPGString(bd: String): Option[BigDecimal] =
    bd match {
      case null | "" =>
        None
      case obd =>
        Some(PGBigDecimalConverter.fromPGString(obd))
    }
}
