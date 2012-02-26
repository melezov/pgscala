package hr.element.pgscala
package builder

object PGUUIDConverterBuilder extends PGConverterBuilder {
  val scalaClazz = "UUID"

  override val imports = "import java.util.UUID"
}