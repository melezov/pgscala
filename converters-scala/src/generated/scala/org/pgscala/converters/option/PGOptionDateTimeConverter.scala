package org.pgscala.converters

import org.joda.time.DateTime

/** Do not edit - generated in Builder / PGDateTimeConverterBuilder.scala */

object PGOptionDateTimeConverter extends PGConverter[Option[DateTime]] {
  val PGType = PGDateTimeConverter.PGType

  def toPGString(odt: Option[DateTime]): String =
    odt match {
      case None =>
        null
      case Some(dt) =>
        PGDateTimeConverter.toPGString(dt)
    }

  def fromPGString(dt: String): Option[DateTime] =
    dt match {
      case null | "" =>
        None
      case odt =>
        Some(PGDateTimeConverter.fromPGString(odt))
    }
}
