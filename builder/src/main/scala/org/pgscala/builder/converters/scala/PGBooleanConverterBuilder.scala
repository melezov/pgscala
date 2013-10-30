package org.pgscala
package builder
package converters

object PGBooleanConverterBuilder
    extends PGPredefConverterBuilder {

  val scalaClazz = "scala.Boolean"

  override val javaClazz = "java.lang.Boolean"
}
