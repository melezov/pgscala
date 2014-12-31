package org.pgscala.converters

import java.net.URL

/** Do not edit - generated in Builder / PGURLConverterBuilder.scala */

object PGOptionURLConverter extends PGConverter[Option[URL]] {
  val PGType = PGURLConverter.PGType

  def toPGString(ourl: Option[URL]): String =
    ourl match {
      case None =>
        null
      case Some(url) =>
        PGURLConverter.toPGString(url)
    }

  def fromPGString(url: String): Option[URL] =
    url match {
      case null | "" =>
        None
      case ourl =>
        Some(PGURLConverter.fromPGString(ourl))
    }
}
