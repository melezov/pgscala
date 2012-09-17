package org.pgscala.converters

import scala.collection.mutable.Map

/** Do not edit - generated in Builder / PGMapConverterBuilder.scala */

object PGOptionMapConverter extends PGConverter[Option[Map[String, String]]] {
  val PGType = PGMapConverter.PGType

  def toPGString(oM: Option[Map[String, String]]): String =
    oM match {
      case None =>
        null
      case Some(m) =>
        PGMapConverter.toPGString(m)
    }

  def fromPGString(m: String): Option[Map[String, String]] =
    m match {
      case null | "" =>
        None
      case oM =>
        Some(PGMapConverter.fromPGString(oM))
    }
}
