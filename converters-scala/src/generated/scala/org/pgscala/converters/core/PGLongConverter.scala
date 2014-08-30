package org.pgscala.converters

/** Do not edit - generated in Builder / PGLongConverterBuilder.scala */

object PGLongConverter extends PGConverter[Long] {
  val PGType = PGNullableLongConverter.pgType

  def toPGString(l: Long) =
    PGNullableLongConverter.longToString(java.lang.Long valueOf l)

  val defaultValue: Long = 0L

  def fromPGString(l: String) =
    if (l eq null)
      defaultValue
    else
      PGNullableLongConverter.stringToLong(l)
}
