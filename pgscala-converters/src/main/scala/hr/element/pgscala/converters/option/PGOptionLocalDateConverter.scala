package hr.element.pgscala.converters

import org.joda.time.LocalDate;

/** Do not edit - generated in PGBuilder / PGLocalDateConverterBuilder.scala */

object PGOptionLocalDateConverter extends PGConverter[Option[LocalDate]] {
  val PGType = PGLocalDateConverter.PGType

  def toPGString(oLD: Option[LocalDate]): String =
    oLD match {
      case None =>
        null
      case Some(lD) =>
        PGLocalDateConverter.toPGString(lD)
    }

  def fromPGString(lD: String): Option[LocalDate] =
    lD match {
      case null | "" =>
        None
      case oLD =>
        Some(PGLocalDateConverter.fromPGString(oLD))
    }
}
