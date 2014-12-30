package org.pgscala
package builder
package converters

object PGBufferedImageConverterBuilder
    extends PGConverterBuilder {
  override def imports = "import java.awt.image.BufferedImage"

  val scalaClazz = "java.awt.image.BufferedImage"

  val defaultValue = "new java.awt.image.BufferedImage(1, 1, java.awt.image.BufferedImage.TYPE_4BYTE_ABGR)"
}
