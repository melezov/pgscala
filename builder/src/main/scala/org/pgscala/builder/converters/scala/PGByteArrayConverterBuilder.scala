package org.pgscala
package builder
package converters

object PGByteArrayConverterBuilder
    extends PGConverterBuilder {

  val scalaClazz = "Array[Byte]"

  override val imports = ""

  override val scalaUpperType = "ByteArray"

  override val javaUpperType = "ByteArray"
}
