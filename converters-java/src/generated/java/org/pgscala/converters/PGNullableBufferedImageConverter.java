package org.pgscala.converters;

import org.joda.convert.*;

import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/** Do not edit - generated in Builder / PGNullableBufferedImageConverterBuilder.scala */

public enum PGNullableBufferedImageConverter implements StringConverter<BufferedImage> {
    INSTANCE;

    public static final String pgType = "bytea";

    @ToString
    public static String bufferedImageToString(final BufferedImage bi) throws IOException {
        if (null == bi) return null;

        final ByteArrayOutputStream baos = new ByteArrayOutputStream(bi.getWidth() * bi.getHeight());
        if (!ImageIO.write(bi, "png", baos)) {
            throw new IOException("Missing PNG write handler (should not happen)");
        }

        return PGNullableByteArrayConverter.byteArrayToString(baos.toByteArray());
    }

    @FromString
    public static BufferedImage stringToBufferedImage(final String bi) throws IOException {
        if (null == bi) return null;

        final ByteArrayInputStream bais = new ByteArrayInputStream(
                PGNullableByteArrayConverter.stringToByteArray(bi));

        return ImageIO.read(bais);
    }

// -----------------------------------------------------------------------------

    public String convertToString(final BufferedImage bi) {
        try {
            return bufferedImageToString(bi);
        } catch (final IOException e) {
            throw new RuntimeException("Could not convert BufferedImage to String");
        }
    }

    public BufferedImage convertFromString(final Class<? extends BufferedImage> clazz, final String bi) {
        try {
            return stringToBufferedImage(bi);
        } catch (final IOException e) {
            throw new RuntimeException("Could not convert String to BufferedImage");
        }
    }
}
