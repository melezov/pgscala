package org.pgscala.converters

/** Do not edit - generated in Builder / PGLongConverterBuilder.scala */

object PGOptionLongConverter extends PGConverter[Option[Long]] {
  val PGType = PGLongConverter.PGType

  def toPGString(ol: Option[Long]): String =
    ol match {
      case None =>
        null
      case Some(l) =>
        PGLongConverter.toPGString(l)
    }

  def fromPGString(l: String): Option[Long] =
    l match {
      case null | "" =>
        None
      case ol =>
        Some(PGLongConverter.fromPGString(ol))
    }
}
