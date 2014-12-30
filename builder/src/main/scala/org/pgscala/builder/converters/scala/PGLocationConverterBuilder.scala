package org.pgscala
package builder
package converters

object PGLocationConverterBuilder
    extends PGConverterBuilder {

  override def imports = "import java.awt.geom._"
  override val scalaUpperType = "Location"

  override val javaUpperType = "Location"

  val scalaClazz = "java.awt.geom.Point2D"

  val defaultValue = """new java.awt.geom.Point2D.Double()"""
}
