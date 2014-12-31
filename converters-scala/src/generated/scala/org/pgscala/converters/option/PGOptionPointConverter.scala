package org.pgscala.converters
import java.awt._
/** Do not edit - generated in Builder / PGPointConverterBuilder.scala */

object PGOptionPointConverter extends PGConverter[Option[Point]] {
  val PGType = PGPointConverter.PGType

  def toPGString(op: Option[Point]): String =
    op match {
      case None =>
        null
      case Some(p) =>
        PGPointConverter.toPGString(p)
    }

  def fromPGString(p: String): Option[Point] =
    p match {
      case null | "" =>
        None
      case op =>
        Some(PGPointConverter.fromPGString(op))
    }
}
