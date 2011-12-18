package hr.element.pgscala.util;

public class PGArray {
  // -----------------------------------------------------------------------------

  /** Prefixes all quotes and backslashes with a backslash. */

  public static String quoteString(final String s) {
    final int len = s.length();
    if (len == 0) {
      return "\"\"";
    }

    final int total;
    {
      int cnt = 0;

      for (int i = 0; i < len; i++) {
        final char ch = s.charAt(i);
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
          quoted[i + 1] = s.charAt(i);
        }
      } else {
        int cnt = 1;
        for (int i = 0; i < len; i++) {
          final char ch = s.charAt(i);
          if (ch == '"' || ch == '\\') {
            quoted[cnt++ + i] = '\\';
          }

          quoted[cnt + i] = ch;
        }
      }

      return new String(quoted);
    }
  }

  public static String quoteOptString(final String s) {
    return s == null ? "NULL" : quoteString(s);
  }

  // -----------------------------------------------------------------------------
}
