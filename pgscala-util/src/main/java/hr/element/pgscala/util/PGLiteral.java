package hr.element.pgscala.util;

public class PGLiteral {

//-----------------------------------------------------------------------------

  /**
   * Replaces all apostrophes with double apostrophes, and surrounds in
   * apostrophes: <code>It's OK!</code> becomes <code>'It''s OK!'</code> This
   * mimics PostgreSQL quote_literal(text) function.
   */

  public static String quote(final String s) {
    if (null == s) {
      return "NULL";
    }

    final int len = s.length();
    if (0 == len)
      return "''";

    final int total;
    {
      int cnt = 0;
      int start = 0;
      while (true) {
        final int ind = s.indexOf('\'', start);

        if (ind == -1) {
          break;
        }

        start = ind + 1;
        cnt++;
      }

      total = cnt;
    }

    final int newLen = total + len + 2;
    final char[] quoted = new char[newLen];
    {
      quoted[0] = quoted[newLen - 1] = '\'';

      if (total == 0) {
        for (int i = 0; i < len; i++) {
          quoted[i + 1] = s.charAt(i);
        }
      } else {
        int cnt = 1;
        for (int i = 0; i < len; i++) {
          char ch = s.charAt(i);
          quoted[cnt + i] = ch;

          if (ch == '\'') {
            quoted[++cnt + i] = ch;
          }
        }
      }
    }

    return new String(quoted);
  }

// -----------------------------------------------------------------------------

  public static String quote(final boolean b) {
    return b ? "'t'" : "'f'";
  }

//-----------------------------------------------------------------------------

  public static String quote(final int i) {
    return Integer.toString(i);
  }

//-----------------------------------------------------------------------------

  public static String quote(final long l) {
    return Long.toString(l);
  }

//-----------------------------------------------------------------------------

  public static String quote(final double d) {
    return Double.toString(d);
  }

//-----------------------------------------------------------------------------

  public static String quote(final float f) {
    return Float.toString(f);
  }

//-----------------------------------------------------------------------------

  private static final char[] HEX_DIGITS =
    "0123456789abcdef".toCharArray();

  public static String quote(final byte[] bA) {
    if (null == bA)
      return null;

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

//-----------------------------------------------------------------------------
}