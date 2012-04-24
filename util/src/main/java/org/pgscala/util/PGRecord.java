package org.pgscala.util;

import java.text.ParseException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    final StringBuilder sB = new StringBuilder("(").append(quote(values[0]));

    for (int i = 1; i < values.length; i++) {
      sB.append(',').append(quote(values[i]));
    }

    return sB.append(')').toString();
  }

  // =============================================================================

  private static final Logger logger =
    LoggerFactory.getLogger(PGRecord.class);

  public static final String[] unpack(final String record)
      throws ParseException {

    if (logger.isTraceEnabled()) {
      logger.trace("Unpacking record: {}", record);
    }

    final int lastIndex = record.length() - 1;
    if (lastIndex < 1)
      throw new IllegalArgumentException("Record length must be >= 2!");

    if ('(' != record.charAt(0))
      throw new ParseException("Illegal character at start of record!", 0);

    if (')' != record.charAt(lastIndex))
      throw new ParseException("Illegal character at end of record!", lastIndex);

    int cur = 1;
    final ArrayList<String> sQ = new ArrayList<String>();
    final StringBuilder sB = new StringBuilder();

    while (cur <= lastIndex) {
      final char ch = record.charAt(cur);
      switch (ch) {

      case ',':
      case ')':
        sQ.add(sB.length() == 0 ? null : sB.toString());
        sB.setLength(0);
        break;

      case '"':
        if (sB.length() > 0) {
          throw new ParseException(
              "Unsupported format (record quote starting in the middle).", cur);
        }

        cur++;
        while (cur < lastIndex) {
          final char ch1 = record.charAt(cur);

          if (ch1 == '\\') {
            cur++;
            sB.append(record.charAt(cur));
          }
          else if (ch1 != '"') {
            sB.append(ch1);
          }
          else {
            cur++;

            final char ch2 = record.charAt(cur);
            if (ch2 != '"') {
              sQ.add(sB.toString());
              sB.setLength(0);
              break;
            } else {
              sB.append(ch2);
            }
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
