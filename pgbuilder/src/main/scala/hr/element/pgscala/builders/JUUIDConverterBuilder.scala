package hr.element.pgscala
package builder

object JUUIDConverterBuilder extends JConverterBuilder {
  override val imports = "import java.util.UUID;"

  val pgType = "text"

  val clazz = "java.util.UUID"

  val to = "uuid.toString()"

  val from = "UUID.fromString(uuid)"
}
