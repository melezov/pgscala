package hr.element.pgscala.converters

import org.joda.time.DateTime;

/** Do not edit - generated in PGBuilder / PGDateTimeConverterBuilder.scala */

object PGOptionDateTimeConverter extends PGConverter[Option[DateTime]] {
  val PGType = PGDateTimeConverter.PGType

  def toPGString(oDT: Option[DateTime]): String =
    oDT match {
      case None =>
        null
      case Some(dT) =>
        PGDateTimeConverter.toPGString(dT)
    }

  def fromPGString(dT: String): Option[DateTime] =
    dT match {
      case null | "" =>
        None
      case oDT =>
        Some(PGDateTimeConverter.fromPGString(oDT))
    }
}
