//package hr.element.pgscala.util;
//
//public class PGArray {
//  // -----------------------------------------------------------------------------
//
//  /** Prefixes all quotes and backslashes with a backslash. */
//
//  public static String quoteString(final String s) {
//    final int len = s.length();
//    if (len == 0) {
//      return "\"\"";
//    }
//
//    final int total;
//    {
//      int cnt = 0;
//
//      for (int i = 0; i < len; i++) {
//        final char ch = s.charAt(i);
//        if (ch == '"' || ch == '\\') {
//          cnt++;
//        }
//      }
//      total = cnt;
//    }
//
//    final int newLen = len + 2 + total;
//    final char[] quoted = new char[newLen];
//    {
//      quoted[0] = quoted[newLen - 1] = '"';
//
//      if (total == 0) {
//        for (int i = 0; i < len; i++) {
//          quoted[i + 1] = s.charAt(i);
//        }
//      } else {
//        int cnt = 1;
//        for (int i = 0; i < len; i++) {
//          final char ch = s.charAt(i);
//          if (ch == '"' || ch == '\\') {
//            quoted[cnt++ + i] = '\\';
//          }
//
//          quoted[cnt + i] = ch;
//        }
//      }
//
//      return new String(quoted);
//    }
//  }
//
//  public static String quoteOptString(final String s) {
//    return s == null ? "NULL" : quoteString(s);
//  }
//
//  // -----------------------------------------------------------------------------
//
//  public static String quoteInteger(final int i) {
//    return Integer.toString(i);
//  }
//
//  public static int unquoteInteger(final String value) {
//    return Integer.valueOf(value);
//  }
//
//  public static String quoteOptInteger(final Integer i) {
//    return i == null ? "NULL" : i.toString();
//  }
//
//  public static Integer unquoteOptInteger(final String value) {
//    return value.equalsIgnoreCase("NULL") ? null : Integer.valueOf(value);
//  }
//
//  // -----------------------------------------------------------------------------
//
//  public static String quoteLong(final long l) {
//    return Long.toString(l);
//  }
//
//  public static long unquoteLong(final String value) {
//    return Long.valueOf(value);
//  }
//
//  public static String quoteOptLong(final Long l) {
//    return l == null ? "NULL" : l.toString();
//  }
//
//  public static Long unquoteOptLong(final String value) {
//    return value.equalsIgnoreCase("NULL") ? null : Long.valueOf(value);
//  }
//
//  // -----------------------------------------------------------------------------
//
//  public static String quoteDouble(final double d) {
//    return Double.toString(d);
//  }
//
//  public static double unquoteDouble(final String value) {
//    return Double.valueOf(value);
//  }
//
//  public static String quoteOptDouble(final Double d) {
//    return d == null ? "NULL" : d.toString();
//  }
//
//  public static Double unquoteOptDouble(final String value) {
//    return value.equalsIgnoreCase("NULL") ? null : Double.valueOf(value);
//  }
//
//  // -----------------------------------------------------------------------------
//
//  public static String quoteFloat(final float f) {
//    return Float.toString(f);
//  }
//
//  public static float unquoteFloat(final String value) {
//    return Float.valueOf(value);
//  }
//
//  public static String quoteOptFloat(final Float f) {
//    return f == null ? "NULL" : f.toString();
//  }
//
//  public static Float unquoteOptFloat(final String value) {
//    return value.equalsIgnoreCase("NULL") ? null : Float.valueOf(value);
//  }
//
//  // -----------------------------------------------------------------------------
//}
