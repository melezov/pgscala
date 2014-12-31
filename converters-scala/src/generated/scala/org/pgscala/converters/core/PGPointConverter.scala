package org.pgscala.converters
import java.awt._
/** Do not edit - generated in Builder / PGPointConverterBuilder.scala */

object PGPointConverter extends PGConverter[Point] {
  val PGType = PGNullablePointConverter.pgType

  def toPGString(p: Point) =
    PGNullablePointConverter.pointToString(p)

  val defaultValue: Point = new java.awt.Point()

  def fromPGString(p: String) =
    if (p eq null)
      defaultValue
    else
      PGNullablePointConverter.stringToPoint(p)
}
