package hr.element.pgscala.util;

public class PGLiteral {

  /** Replaces all apostrophes with double apostrophes, and surrounds in apostrophes:
    *   <code>It's OK!</code> becomes <code>'It''s OK!'</code>
    * This mimics PostgreSQL quote_literal(text) function. */

  public static String quoteLiteral(final String literal)
  {
    if (null == literal) {
      return "NULL";
    }

    int len = literal.length();
    if (0 == len) {
      return "''";
    }

    final int total;
    {
      int cnt = 0;
      int start = 0;
      while (true) {
        final int ind = literal.indexOf('\'', start);

        if (ind == -1) break;
        start = ind + 1;

        cnt++;
      }

      total = cnt;
    }

    int newLen = total + len + 2;

    char[] quoted = new char[newLen];
    {
      quoted[0] = quoted[newLen - 1] = '\'';

      if (total == 0) {
        for (int i = 0; i < len;) {
          quoted[++i] = literal.charAt(i);
        }
      }
      else {
        int cnt = 1;
        for (int i = 0; i < len; i++) {
          char ch = literal.charAt(i);
          quoted[cnt + i] = ch;

          if (ch == '\'')
            quoted[++cnt + i] = ch;
        }
      }
    }

    return new String(quoted);
  }
}