package hr.element.pgscala.util;

import java.util.HashMap;
import java.util.Map;

public class PGLiteral {
  // -----------------------------------------------------------------------------

  /**
   * Replaces all apostrophes with double apostrophes, and surrounds in
   * apostrophes: <code>It's OK!</code> becomes <code>'It''s OK!'</code> This
   * mimics PostgreSQL quote_literal(text) function.
   */

  public static String quoteString(final String s) {
    final int len = s.length();
    if (len == 0) {
      return "''";
    }

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

    final int newLen = len + 2 + total;
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
          final char ch = s.charAt(i);
          quoted[cnt + i] = ch;

          if (ch == '\'') {
            quoted[++cnt + i] = ch;
          }
        }
      }
    }

    return new String(quoted);
  }

  public static String unquoteString(final String value) {
    final int len = value.length();
    if (len == 2) {
      return "";
    }

    final int total;
    {
      int cnt = -1;
      int start = 1;
      while (true) {
        final int ind = value.indexOf('\'', start);
        if (ind == -1) {
          break;
        }

        start = ind + 1;
        cnt++;
      }
      total = cnt;
    }

    if (total == 0) {
      return value.substring(1, len - 1);
    }

    final int newLen = len - 2 - total;
    final char[] unquoted = new char[newLen];

    int index = 1;
    for (int i = 0; i < newLen; i++) {
      final char ch = unquoted[i] = value.charAt(index);

      if (ch == '\'') {
        index ++;
      }
    }

    return new String(unquoted);
  }

  public static String quoteOptString(final String s) {
    return s == null ? "NULL" : quoteString(s);
  }

  public static String unquoteOptString(final String value) {
    return value.equalsIgnoreCase("NULL") ? null : unquoteString(value);
  }

  // -----------------------------------------------------------------------------

  private static Map<String,Boolean> boolMap =
      new HashMap<String,Boolean>();
    static {
      boolMap.put("null",   null);

      boolMap.put("true",   Boolean.TRUE);
      boolMap.put("'t'",    Boolean.TRUE);
      boolMap.put("'true'", Boolean.TRUE);
      boolMap.put("'y'",    Boolean.TRUE);
      boolMap.put("'yes'",  Boolean.TRUE);
      boolMap.put("'1'",    Boolean.TRUE);

      boolMap.put("false",   Boolean.FALSE);
      boolMap.put("'f'",     Boolean.FALSE);
      boolMap.put("'false'", Boolean.FALSE);
      boolMap.put("'n'",     Boolean.FALSE);
      boolMap.put("'no'",    Boolean.FALSE);
      boolMap.put("'0'",     Boolean.FALSE);
    }

  public static String quoteBoolean(final boolean b) {
    return b ? "'t'" : "'f'";
  }

  public static boolean unquoteBoolean(final String value) {
    return boolMap.get(value.toLowerCase());
  }

  public static String quoteOptBoolean(final Boolean b) {
    return (b == null) ? "NULL" : b ? "'t'" : "'f'";
  }

  public static Boolean unquoteOptBoolean(final String value) {
    return boolMap.get(value.toLowerCase());
  }

  // -----------------------------------------------------------------------------

  public static String quoteInteger(final int i) {
    return Integer.toString(i);
  }

  public static int unquoteInteger(final String value) {
    return Integer.valueOf(value);
  }

  public static String quoteOptInteger(final Integer i) {
    return i == null ? "NULL" : i.toString();
  }

  public static Integer unquoteOptInteger(final String value) {
    return value.equalsIgnoreCase("NULL") ? null : Integer.valueOf(value);
  }

  // -----------------------------------------------------------------------------

  public static String quoteLong(final long l) {
    return Long.toString(l);
  }

  public static long unquoteLong(final String value) {
    return Long.valueOf(value);
  }

  public static String quoteOptLong(final Long l) {
    return l == null ? "NULL" : l.toString();
  }

  public static Long unquoteOptLong(final String value) {
    return value.equalsIgnoreCase("NULL") ? null : Long.valueOf(value);
  }

  // -----------------------------------------------------------------------------

  public static String quoteDouble(final double d) {
    return Double.toString(d);
  }

  public static double unquoteDouble(final String value) {
    return Double.valueOf(value);
  }

  public static String quoteOptDouble(final Double d) {
    return d == null ? "NULL" : d.toString();
  }

  public static Double unquoteOptDouble(final String value) {
    return value.equalsIgnoreCase("NULL") ? null : Double.valueOf(value);
  }

  // -----------------------------------------------------------------------------

  public static String quoteFloat(final float f) {
    return Float.toString(f);
  }

  public static float unquoteFloat(final String value) {
    return Float.valueOf(value);
  }

  public static String quoteOptFloat(final Float f) {
    return f == null ? "NULL" : f.toString();
  }

  public static Float unquoteOptFloat(final String value) {
    return value.equalsIgnoreCase("NULL") ? null : Float.valueOf(value);
  }

  // -----------------------------------------------------------------------------

  private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
  private static final byte[] REVERSE_HEX = new byte['f' + 1];
  static {
    for (int i = 0; i < 16; i ++){
      REVERSE_HEX[i] = (byte) i;
    }
    for (int i = 10; i < 16; i ++){
      REVERSE_HEX['A' - 10 + i] = REVERSE_HEX['a' - 10 + i] = (byte) i;
    }
  }

  public static String quoteByteArray(final byte[] bA) {
    final int len = bA.length;
    if (len == 0) {
      return "''";
    }

    final char[] buffer = new char[(len + 2) << 1];
    {
      buffer[0] = buffer[len - 1] = '\'';
      buffer[1] = '\\';
      buffer[2] = 'x';

      int index = 3;
      for (int i = 0; i < len; i++) {
        final byte b = bA[i];
        buffer[index++] = HEX_DIGITS[b & 0xf0 >>> 4];
        buffer[index++] = HEX_DIGITS[b & 0xf];
      }
    }

    return new String(buffer);
  }

  public static byte[] unquoteByteArray(final String value) {
    final int len = value.length();
    if ((len == 2) || (len == 4)) {
      return new byte[0];
    }

    final int newLen = (len >>> 1) - 2;
    final byte[] buffer = new byte[len];

    int index = 3;
    for (int i = 0; i < newLen; i++) {
      final char chH = value.charAt(index ++);
      final char chL = value.charAt(index ++);
      buffer[i] = (byte) ( (REVERSE_HEX[chH] << 4) + REVERSE_HEX[chL]);
    }

    return buffer;
  }

  public static String quoteOptByteArray(final byte[] bA) {
    return bA == null ? "NULL" : quoteByteArray(bA);
  }

  public static byte[] unquoteOptByteArray(final String value) {
    return value.equalsIgnoreCase("NULL") ? null : unquoteByteArray(value);
  }

  // -----------------------------------------------------------------------------
}
