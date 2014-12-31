package org.pgscala.converters
import java.awt.geom._
/** Do not edit - generated in Builder / PGLocationDoubleConverterBuilder.scala */

object PGOptionLocationDoubleConverter extends PGConverter[Option[java.awt.geom.Point2D.Double]] {
  val PGType = PGLocationDoubleConverter.PGType

  def toPGString(old: Option[java.awt.geom.Point2D.Double]): String =
    old match {
      case None =>
        null
      case Some(ld) =>
        PGLocationDoubleConverter.toPGString(ld)
    }

  def fromPGString(ld: String): Option[java.awt.geom.Point2D.Double] =
    ld match {
      case null | "" =>
        None
      case old =>
        Some(PGLocationDoubleConverter.fromPGString(old))
    }
}
