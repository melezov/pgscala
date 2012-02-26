package hr.element.pgscala.converters

/** Do not edit - generated in PGBuilder / PGBigDecimalConverterBuilder.scala */

object PGOptionBigDecimalConverter extends PGConverter[Option[BigDecimal]] {
  val PGType = PGBigDecimalConverter.PGType

  def toPGString(oBD: Option[BigDecimal]): String =
    oBD match {
      case None =>
        null
      case Some(bD) =>
        PGBigDecimalConverter.toPGString(bD)
    }

  def fromPGString(bD: String): Option[BigDecimal] =
    bD match {
      case null | "" =>
        None
      case oBD =>
        Some(PGBigDecimalConverter.fromPGString(oBD))
    }
}
