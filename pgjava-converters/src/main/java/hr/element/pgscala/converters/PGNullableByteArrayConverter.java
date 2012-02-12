package hr.element.pgscala.converters;

import org.joda.convert.*;

public class PGNullableByteArrayConverter {
  public static final String pgType = "bytea";

  private static final char[] HEX_DIGITS =
    "0123456789abcdef".toCharArray();

  @ToString
  public static String toPGString(final byte[] bA) {
    return null == bA ? null : bA.toString();
  }

  @FromString
  public static byte[] fromPGString(final String bA) {
    return null == bA ? null : null;
  }
}
/*

private val HexIndexes = {
  val buf = new Array[Byte]('f' + 1)
  for (i <- 0 to 15) {
    if (i < 10) {
      buf('0' + i) = i.toByte
    }
    else {
      buf('a' - 10 + i) = i.toByte
      buf('A' - 10 + i) = i.toByte
    }
  }
  buf
}

def toString(bA: Array[Byte]): String = {
  ""

}
*/