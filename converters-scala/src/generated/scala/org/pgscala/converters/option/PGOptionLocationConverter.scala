package org.pgscala.converters
import java.awt.geom._
/** Do not edit - generated in Builder / PGLocationConverterBuilder.scala */

object PGOptionLocationConverter extends PGConverter[Option[java.awt.geom.Point2D.Double]] {
  val PGType = PGLocationConverter.PGType

  def toPGString(ol: Option[java.awt.geom.Point2D.Double]): String =
    ol match {
      case None =>
        null
      case Some(l) =>
        PGLocationConverter.toPGString(l)
    }

  def fromPGString(l: String): Option[java.awt.geom.Point2D.Double] =
    l match {
      case null | "" =>
        None
      case ol =>
        Some(PGLocationConverter.fromPGString(ol))
    }
}
