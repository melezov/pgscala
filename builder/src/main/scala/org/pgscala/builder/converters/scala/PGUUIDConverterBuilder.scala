package org.pgscala
package builder
package converters

object PGUUIDConverterBuilder
    extends PGConverterBuilder {

  val scalaClazz = "UUID"

  override val imports = "import java.util.UUID"
}
