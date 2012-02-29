package org.pgscala
package builder
package converters

object PGLongConverterBuilder extends PGPredefConverterBuilder {
  val scalaClazz = "scala.Long"

  override val javaClazz = "java.lang.Long"
}
