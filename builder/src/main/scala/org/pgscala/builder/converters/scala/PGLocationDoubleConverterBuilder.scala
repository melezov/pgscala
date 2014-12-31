package org.pgscala
package builder
package converters

object PGLocationDoubleConverterBuilder
    extends PGConverterBuilder {

  override def imports = "import java.awt.geom._"
  override val scalaUpperType = "LocationDouble"

  override val javaUpperType = "LocationDouble"

  val scalaClazz = "java.awt.geom.Point2D.Double"
  override val scalaType  = "java.awt.geom.Point2D.Double"

  val defaultValue = """new java.awt.geom.Point2D.Double()"""
}
