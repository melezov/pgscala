package org.pgscala
package builder
package converters

object PGDoubleConverterBuilder
    extends PGPredefConverterBuilder {

  val scalaClazz = "scala.Double"

  override val javaClazz = "java.lang.Double"

  override val defaultValue = """0.0"""
}
