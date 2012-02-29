package org.pgscala.util;

import java.text.ParseException;
import java.util.ArrayList;

public final class PGArray {

  /**
   * Prefixes all quotes and backslashes with a backslash, and puts the string
   * in quotes if the element contains , \ " { } or a space, or if it is "NULL".
   */

  public static final String quote(final String element) {
    if (null == element) {
      return "NULL";
    }

    final int len = element.length();
    if (len == 0) {
      return "\"\"";
    }

    final int total;
    {
      int cnt = 0;
      boolean quoting = false;

      for (int i = 0; i < len; i++) {
        final char ch = element.charAt(i);
        if (ch == '"' || ch == '\\') {
          cnt++;
          quoting = true;
        } else if (ch == ',' || ch == '\\' || ch == '{' || ch == '}'
            || Character.isWhitespace(ch)) {
          quoting = true;
        }
      }

      if (!quoting && !element.equalsIgnoreCase("NULL")) {
        return element;
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

  public static final String pack(final String[] elements) {
    if (elements.length == 0) {
      return "{}";
    }

    final StringBuilder sB = new StringBuilder("{").append(quote(elements[0]));

    for (int i = 1; i < elements.length; i++) {
      sB.append(',').append(quote(elements[i]));
    }

    return sB.append('}').toString();
  }

  // =============================================================================

  public static final String[] unpack(final String array) throws ParseException {

    final int lastIndex = array.length() - 1;
    if (lastIndex < 1)
      throw new IllegalArgumentException("Array length must be >= 2!");

    if ('{' != array.charAt(0))
      throw new ParseException("Illegal character at start of array!", 0);

    if ('}' != array.charAt(lastIndex))
      throw new ParseException("Illegal character at end of array!", lastIndex);

    if (lastIndex == 1)
      return new String[0];

    int cur = 1;
    final ArrayList<String> sQ = new ArrayList<String>();
    final StringBuilder sB = new StringBuilder();

    while (cur <= lastIndex) {
      final char ch = array.charAt(cur);
      switch (ch) {
      case ',':
      case '}':
        final String res = sB.toString();
        sQ.add(res.equals("NULL") ? null : res);
        sB.setLength(0);
        break;

      case '"':
        if (sB.length() > 0) {
          throw new ParseException(
            "Unsupported format (array quote starting in the middle).", cur);
        }

        cur++;
        while (cur < lastIndex) {
          final char ch1 = array.charAt(cur);
          if (ch1 == '\\') {
            cur++;

            final char ch2 = array.charAt(cur);
            sB.append(ch2);
          } else if (ch1 == '"') {
            cur++;

            final char ch2 = array.charAt(cur);
            if (ch2 == '"') {
              sB.append(ch2);
            } else {
              sQ.add(sB.toString());
              sB.setLength(0);
              break;
            }
          } else {
            sB.append(ch1);
          }
          cur++;
        }
        break;

      default:
        sB.append(ch);
      }

      cur++;
    }

    if (sB.length() > 0) {
      throw new ParseException("Extra content at the end!", cur);
    }

    return sQ.toArray(new String[sQ.size()]);
  }
}
