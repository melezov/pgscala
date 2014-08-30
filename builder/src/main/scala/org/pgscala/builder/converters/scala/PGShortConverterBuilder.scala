package org.pgscala
package builder
package converters

object PGShortConverterBuilder
    extends PGPredefConverterBuilder {

  val scalaClazz = "scala.Short"

  override val javaClazz = "java.lang.Short"

  val defaultValue = "0.toShort"
}
