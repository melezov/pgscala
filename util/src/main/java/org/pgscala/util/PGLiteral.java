package org.pgscala.util;

public final class PGLiteral {
  // -----------------------------------------------------------------------------

  /**
   * Replaces all apostrophes with double apostrophes, and surrounds in
   * apostrophes: <code>It's OK!</code> becomes <code>'It''s OK!'</code> This
   * mimics PostgreSQL quote_literal(text) function.
   */

  public static final String quote(final String literal) {
    final int len = literal.length();
    if (len == 0) {
      return "''";
    }

    final int total;
    {
      int cnt = 0;
      int start = 0;
      while (true) {
        final int ind = literal.indexOf('\'', start);
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
          quoted[i + 1] = literal.charAt(i);
        }
      } else {
        int cnt = 1;
        for (int i = 0; i < len; i++) {
          final char ch = literal.charAt(i);
          quoted[cnt + i] = ch;

          if (ch == '\'') {
            quoted[++cnt + i] = ch;
          }
        }
      }
    }

    return new String(quoted);
  }

  public static final String unquote(final String value) {
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

    final int newLen = len - 2 - (total >>> 1);
    final char[] unquoted = new char[newLen];

    int index = 1;
    for (int i = 0; i < newLen; i++) {
      final char ch = unquoted[i] = value.charAt(index++);

      if (ch == '\'') {
        index++;
      }
    }

    return new String(unquoted);
  }
}
