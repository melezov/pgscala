package org.pgscala.converters
import java.awt.geom._
/** Do not edit - generated in Builder / PGLocationConverterBuilder.scala */

object PGOptionLocationConverter extends PGConverter[Option[Point2D]] {
  val PGType = PGLocationConverter.PGType

  def toPGString(ol: Option[Point2D]): String =
    ol match {
      case None =>
        null
      case Some(l) =>
        PGLocationConverter.toPGString(l)
    }

  def fromPGString(l: String): Option[Point2D] =
    l match {
      case null | "" =>
        None
      case ol =>
        Some(PGLocationConverter.fromPGString(ol))
    }
}
