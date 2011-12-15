package hr.element.pgscala.util;

public class PGArray {
  // -----------------------------------------------------------------------------

  /** Prefixes all quotes and backslashes with a backslash. */

  public static String quote(final String element) {
    if (null == element)
      return "NULL";

    final int len = element.length();
    if (0 == len) {
      return "\"\"";
    }

    final int total;
    {
      int cnt = 0;

      for (int i = 0; i < len; i++) {
        final char ch = element.charAt(i);
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
          quoted[i + 1] = element.charAt(i);
        }
      } else {
        int cnt = 1;
        for (int i = 0; i < len; i++) {
          final char ch = element.charAt(i);
          if (ch == '"' || ch == '\\') {
            quoted[cnt++ + i] = '\\';
          }

          quoted[cnt + i] = ch;
        }
      }

      return new String(quoted);
    }
  }

  // -----------------------------------------------------------------------------
}
