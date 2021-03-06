package org.pgscala.converters;

import org.joda.convert.*;

/** Do not edit - generated in Builder / PGNullableByteArrayConverterBuilder.scala */

public enum PGNullableByteArrayConverter implements StringConverter<byte[]> {
    INSTANCE;

    public static final String pgType = "bytea";

    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
    private static final int[] HEX_INDEXES = new int['f' + 1];
    static {
        for (int i = 0; i < HEX_DIGITS.length; i++) {
            HEX_INDEXES[HEX_DIGITS[i]] = i;
        }
    }

    @ToString
    public static String byteArrayToString(final byte[] ba) {
        if (null == ba) return null;

        final int len = ba.length;
        if (len == 0) return "";

        final char[] buffer = new char[(len + 1) << 1];
        buffer[0] = '\\';
        buffer[1] = 'x';

        int index = 2;
        for (int i = 0; i < len; i++) {
            final byte b = ba[i];
            buffer[index++] = HEX_DIGITS[(b & 0xf0) >>> 4];
            buffer[index++] = HEX_DIGITS[b & 0xf];
        }

        return new String(buffer);
    }

    @FromString
    public static byte[] stringToByteArray(final String ba) {
        if (null == ba) return null;

        final int len = ba.length();
        if ((len == 0) || (len == 2)) return new byte[0];

        final int newLen = (len - 2) >> 1;
        final byte[] buffer = new byte[newLen];

        int index = 2;
        for (int i = 0; i < newLen; i++) {
            final char chH = ba.charAt(index++);
            final char chL = ba.charAt(index++);
            buffer[i] = (byte) ((HEX_INDEXES[chH] << 4) + HEX_INDEXES[chL]);
        }

        return buffer;
    }

// -----------------------------------------------------------------------------

    public String convertToString(final byte[] ba) {
        return byteArrayToString(ba);
    }

    public byte[] convertFromString(final Class<? extends byte[]> clazz, final String ba) {
        return stringToByteArray(ba);
    }
}
