package org.pgscala
package builder
package converters

object PGMapConverterBuilder
    extends PGConverterBuilder {

  val scalaClazz = "Map[String, String]"

  override val javaUpperType = "Map"

  override val scalaUpperType = "Map"

  override val imports = ""

  val defaultValue = "Map.empty[String, String]"
}
