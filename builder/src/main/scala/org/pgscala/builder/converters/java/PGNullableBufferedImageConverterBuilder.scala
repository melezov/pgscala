package org.pgscala
package builder
package converters

object PGNullableBufferedImageConverterBuilder extends PGNullableConverterBuilder {
  override val imports = """
import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
"""

  val pgType = "bytea"

  val clazz = "java.awt.image.BufferedImage"

  val to = """
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(bi.getWidth() * bi.getHeight());
        if (!ImageIO.write(bi, "png", baos)) {
            throw new IOException("Missing PNG write handler (should not happen)");
        }

        return PGNullableByteArrayConverter.byteArrayToString(baos.toByteArray())"""

  val from = """
        final ByteArrayInputStream bais = new ByteArrayInputStream(
                PGNullableByteArrayConverter.stringToByteArray(bi));

        return ImageIO.read(bais)"""

  override val toThrowsExceptions = Seq("IOException")
  override val fromThrowsExceptions = Seq("IOException")

  override def inject(body: String) =
    super.inject(body)
      .replace(
        "return null == bi ? null :",
        """if (null == bi) return null;
"""
      ).replaceFirst("\\};", "}");
}
