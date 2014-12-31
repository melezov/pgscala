package org.pgscala.converters
import java.awt.geom._
/** Do not edit - generated in Builder / PGLocationDoubleConverterBuilder.scala */

object PGLocationDoubleConverter extends PGConverter[java.awt.geom.Point2D.Double] {
  val PGType = PGNullableLocationDoubleConverter.pgType

  def toPGString(ld: java.awt.geom.Point2D.Double) =
    PGNullableLocationDoubleConverter.locationDoubleToString(ld)

  val defaultValue: java.awt.geom.Point2D.Double = new java.awt.geom.Point2D.Double()

  def fromPGString(ld: String) =
    if (ld eq null)
      defaultValue
    else
      PGNullableLocationDoubleConverter.stringToLocationDouble(ld)
}
