package org.pgscala.converters
import java.awt.geom._
/** Do not edit - generated in Builder / PGLocationConverterBuilder.scala */

object PGLocationConverter extends PGConverter[java.awt.geom.Point2D.Double] {
  val PGType = PGNullableLocationConverter.pgType

  def toPGString(l: java.awt.geom.Point2D.Double) =
    PGNullableLocationConverter.locationToString(l)

  val defaultValue: Point2D.Double = new java.awt.geom.Point2D.Double()

  def fromPGString(l: String): java.awt.geom.Point2D.Double =
    if (l eq null)
      defaultValue
    else
      PGNullableLocationConverter.stringToLocation(l).asInstanceOf[Point2D.Double]
}
