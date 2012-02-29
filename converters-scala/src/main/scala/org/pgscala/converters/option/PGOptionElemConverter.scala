package org.pgscala.converters

import scala.xml.Elem

/** Do not edit - generated in Builder / PGElemConverterBuilder.scala */

object PGOptionElemConverter extends PGConverter[Option[Elem]] {
  val PGType = PGElemConverter.PGType

  def toPGString(oE: Option[Elem]): String =
    oE match {
      case None =>
        null
      case Some(e) =>
        PGElemConverter.toPGString(e)
    }

  def fromPGString(e: String): Option[Elem] =
    e match {
      case null | "" =>
        None
      case oE =>
        Some(PGElemConverter.fromPGString(oE))
    }
}
