package hr.element.pgscala.util;

public class PGRecord {

  /**
   * Doubles all quotes and backslashes (for quoting a tuple element).
   * <code>It's: "OK"!</code> becomes <code>"It's ""OK""!"</code>
   */

  public static String quote(final String value) {
    if (null == value) {
      return null;
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

    final int newLen = total + len + 2;
    final char[] quoted = new char[newLen];
    {
      quoted[0] = quoted[newLen - 1] = '"';

      if (total == 0) {
        for (int i = 0; i < len; i++) {
          quoted[i + 1] = value.charAt(i);
        }
      }
      else {
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

  // public static final String toString(final String[] values) {
  // if (null == values)
  // return null;
  //
  // final StringBuilder sB = new StringBuilder("(");
  //
  // for (int i = 0; i < values.length; i++) {
  // if (i > 0)
  // sB.append(',');
  //
  // final String cur = PGUtil.quote(values[i]);
  // sB.append(cur);
  // }
  //
  // return sB.append(')').toString();
  // }
  //
  // /** Removes backslashes and unescapes quotes.
  // * Converts empty strings into null values. */
  //
  // public static String unquote(final String value)
  // throws ParseException {
  //
  // if (value.isEmpty())
  // return null;
  //
  // final boolean quoting = '"' == value.charAt(0);
  // if (!quoting) return value;
  //
  // final int lastIndex = value.length() - 1;
  //
  // if ('"' != value.charAt(lastIndex))
  // throw new ParseException("Quoted value does not end with quote",
  // lastIndex);
  //
  // final StringBuilder sB = new StringBuilder();
  //
  // for (int index = 1; index < lastIndex; index ++) {
  // final char ch = value.charAt(index);
  //
  // if (('\\' == ch) || ('"' == ch)) {
  // if (++ index >= lastIndex)
  // throw new ParseException("Premature end, escaped value could not be read",
  // index);
  //
  // sB.append(value.charAt(index));
  // }
  // else {
  // sB.append(ch);
  // }
  // }
  //
  // return sB.toString();
  // }
  //
  // public static final String[] fromString(final String record)
  // throws ParseException {
  //
  // final int lastIndex = record.length() - 1;
  // if (lastIndex < 1)
  // throw new IllegalArgumentException("Record length must be >= 2!");
  //
  // if ('(' != record.charAt(0))
  // throw new ParseException("Illegal character at start of record!", 0);
  //
  // if (')' != record.charAt(lastIndex))
  // throw new ParseException("Illegal character at end of record!", lastIndex);
  //
  // int index = 1;
  // final Queue<String> sQ = new ArrayDeque<String>();
  //
  // int curIndex = 1;
  //
  // while (index <= lastIndex) {
  // final char ch = record.charAt(index);
  // if (',' == ch) {
  // final String value =
  // unquote(record.substring(curIndex, index - 1));
  //
  // sQ.add(value);
  // curIndex = index + 1;
  // }
  //
  // index ++;
  // }
  //
  // return sQ.toArray(new String[sQ.size()]);
  // }

}
