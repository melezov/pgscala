package org.pgscala
package builder
package converters

object PGElemConverterBuilder
    extends PGConverterBuilder {

  val scalaClazz = "Elem"

  override val imports = "import scala.xml.Elem"

  val defaultValue = "null // no sane default for XML"
}
