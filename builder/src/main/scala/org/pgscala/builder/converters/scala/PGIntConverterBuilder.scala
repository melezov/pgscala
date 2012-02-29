package org.pgscala
package builder
package converters

object PGIntConverterBuilder extends PGPredefConverterBuilder {
  val scalaClazz = "scala.Int"

  override val javaClazz = "java.lang.Integer"
}
