package hr.element.pgscala
package builders

object ByteArrayBuilder extends Builder {
  val clazz = "byte[]"

  override def fileName = "ByteArray"

  val pgType = "bytea"

  override val pre =
"""  private static final char[] HEX_DIGITS =
    "0123456789abcdef".toCharArray();

  private static final int[] HEX_INDEXES = new int['f' + 1];
  static {
    for (int i = 0; i < HEX_DIGITS.length; i++) {
      HEX_INDEXES[HEX_DIGITS[i]] = i;
    }
  }
"""

  val to = """

    final int len = bA.length;
    if (len == 0) {
      return "";
    }

    final char[] buffer = new char[(len + 1) << 1];
    {
      buffer[0] = '\\';
      buffer[1] = 'x';

      int index = 2;
      for (int i = 0; i < len; i++) {
        final byte b = bA[i];
        buffer[index++] = HEX_DIGITS[(b & 0xf0) >>> 4];
        buffer[index++] = HEX_DIGITS[b & 0xf];
      }
    }

    return new String(buffer)"""

  val from = """

    final int len = bA.length();
    if ((len == 0) || (len == 2)) {
      return new byte[0];
    }

    final int newLen = ((len - 2) >> 1);
    final byte[] buffer = new byte[newLen];

    int index = 2;
    for (int i = 0; i < newLen; i++) {
      final char chH = bA.charAt(index++);
      final char chL = bA.charAt(index++);
      buffer[i] = (byte) ((HEX_INDEXES[chH] << 4)
                         + HEX_INDEXES[chL]);
    }

    return buffer"""

  override def inject(body: String) = {
    super.inject(body)
      .replace(
        "return null == bA ? null :",
        "if (null == bA) return null;"
      )
  }

  override def jasminClass = "[B"
}
