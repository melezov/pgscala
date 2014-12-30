package org.pgscala.converters
import java.awt.geom._
/** Do not edit - generated in Builder / PGLocationConverterBuilder.scala */

object PGLocationConverter extends PGConverter[Point2D] {
  val PGType = PGNullableLocationConverter.pgType

  def toPGString(l: Point2D) =
    PGNullableLocationConverter.locationToString(l)

  val defaultValue: Point2D = new java.awt.geom.Point2D.Double()

  def fromPGString(l: String) =
    if (l eq null)
      defaultValue
    else
      PGNullableLocationConverter.stringToLocation(l)
}
