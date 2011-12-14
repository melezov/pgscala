package hr.element.pgscala.util;

public class PGQuote {
  public static String quote(final String s) {
    return PGLiteral.quoteLiteral(s);
  }

  public static String quote(final boolean b) {
    return b ? "'t'" : "'f'";
  }

  public static String quote(final int i) {
    return Integer.toString(i);
  }

  public static String quote(final long l) {
    return Long.toString(l);
  }

  public static String quote(final double d) {
    return Double.toString(d);
  }

  public static String quote(final float f) {
    return Float.toString(f);
  }

  private static final char[] HEX_DIGITS =
    "0123456789abcdef".toCharArray();

  public static String quote(final byte[] bA) {
    final int len = bA.length;
    final char[] buffer = new char[(len + 2) << 1];
    {
      buffer[0] = buffer[len - 1] = '\'';
      buffer[1] = '\\';
      buffer[2] = 'x';

      int index = 3;
      for (int i = 0; i < len; i ++) {
        final byte b = bA[i];
        buffer[index ++] = HEX_DIGITS[b & 0xf0 >>> 4];
        buffer[index ++] = HEX_DIGITS[b & 0xf];
      }
    }

    return new String(buffer);
  }
}