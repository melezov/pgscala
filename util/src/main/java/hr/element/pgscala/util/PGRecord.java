package hr.element.pgscala.util;

public final class PGRecord {
  // -----------------------------------------------------------------------------

  /**
   * Doubles all quotes and backslashes (for quoting a tuple value).
   * <code>It's: "OK"!</code> becomes <code>"It's ""OK""!"</code>
   *
   * If the value is not empty and does not contain characters
   * , \ ( ) or spaces the value does not need to be quoted.
   */

  public static final String quote(final String value) {
    if (null == value) {
      return "";
    }

    final int len = value.length();
    if (len == 0) {
      return "\"\"";
    }

    final int total;
    {
      int cnt = 0;
      boolean quoting = false;

      for (int i = 0; i < len; i++) {
        final char ch = value.charAt(i);
        if (ch == '"' || ch == '\\') {
          cnt++;
          quoting = true;
        }
        else if (ch == ',' || ch == '\\' ||
                 ch == '(' || ch == ')' ||
                 Character.isWhitespace(ch)) {
          quoting = true;
        }
      }

      if (!quoting) {
        return value;
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
          final char ch = value.charAt(i);
          quoted[cnt + i] = ch;

          if (ch == '"' || ch == '\\') {
            quoted[++cnt + i] = ch;
          }
        }
      }
    }

    return new String(quoted);
  }

  public static final String pack(final String[] values) {
    if (values.length == 0) {
      return "()";
    }

    final StringBuilder sB =
      new StringBuilder('(').append(values[0]);

    for (int i = 1; i < values.length; i++) {
      sB.append(',').append(quote(values[i]));
    }

    return sB.append(')').toString();
  }

  // =============================================================================
}