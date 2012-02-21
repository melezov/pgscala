package hr.element.pgscala
package builder

object UUIDConverterBuilder extends SConverterBuilder {
  val scalaClazz = "UUID"

  override val imports = "import java.util.UUID"
}