package hr.element.pgscala
package builder

object ByteArrayConverterBuilder extends SConverterBuilder {
  val scalaClazz = "Array[Byte]"

  override val imports = ""

  override val scalaUpperType = "ByteArray"

  override val javaUpperType = "ByteArray"
}