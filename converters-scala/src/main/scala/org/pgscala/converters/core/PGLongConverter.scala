package org.pgscala.converters

/** Do not edit - generated in PGBuilder / PGLongConverterBuilder.scala */

object PGLongConverter extends PGConverter[Long] {
  val PGType = PGNullableLongConverter.pgType

  def toPGString(l: Long) =
    PGNullableConverter.toPGString(java.lang.Long valueOf l)

  def fromPGString(l: String) =
    PGNullableConverter.fromPGString(l)
}
