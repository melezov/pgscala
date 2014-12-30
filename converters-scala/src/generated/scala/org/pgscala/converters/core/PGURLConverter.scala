package org.pgscala.converters

import java.net.URL

/** Do not edit - generated in Builder / PGURLConverterBuilder.scala */

object PGURLConverter extends PGConverter[URL] {
  val PGType = PGNullableURLConverter.pgType

  def toPGString(url: URL) =
    PGNullableURLConverter.urlToString(url)

  val defaultValue: URL = null

  def fromPGString(url: String) =
    if (url eq null)
      defaultValue
    else
      PGNullableURLConverter.stringToURL(url)
}
