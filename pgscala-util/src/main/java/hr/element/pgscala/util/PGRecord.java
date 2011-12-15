package hr.element.pgscala.util;

public class PGRecord {
  // -----------------------------------------------------------------------------

  /**
   * Doubles all quotes and backslashes (for quoting a tuple element).
   * <code>It's: "OK"!</code> becomes <code>"It's ""OK""!"</code>
   */

  public static String quote(final String value) {
    if (null == value) {
      return "";
    }

    final int len = value.length();
    if (0 == len) {
      return "\"\"";
    }

    final int total;
    {
      int cnt = 0;
      for (int i = 0; i < len; i++) {
        final char ch = value.charAt(i);
        if (ch == '"' || ch == '\\') {
          cnt++;
        }
      }
      total = cnt;
    }

    final int newLen = len + 2 + total;
    final char[] quoted = new char[newLen];
    {
      quoted[0] = quoted[newLen - 1] = '"';

      if (total == 0) {
        for (int i = 0; i < len; i++) {
          quoted[i + 1] = value.charAt(i);
        }
      } else {
        int cnt = 1;
        for (int i = 0; i < len; i++) {
          char ch = value.charAt(i);
          quoted[cnt + i] = ch;

          if (ch == '"' || ch == '\\') {
            quoted[++cnt + i] = ch;
          }
        }
      }
    }

    return new String(quoted);
  }

  // -----------------------------------------------------------------------------
}
