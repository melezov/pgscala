package org.pgscala
package builder
package converters

object PGPointConverterBuilder
    extends PGConverterBuilder {

  override def imports = "import java.awt._"

  override val scalaUpperType = "Point"

  override val javaUpperType = "Point"

  val scalaClazz = "java.awt.Point"

  val defaultValue = """new java.awt.Point()"""
}
