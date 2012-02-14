package hr.element.pgscala.converters;

import org.joda.convert.*;

public class PGNullableByteArrayConverter {
	public static final String pgType = "bytea";

	private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();

	private static final int[] HEX_INDEXES = new int['f' + 1];
	static {
		for (int i = 0; i < HEX_DIGITS.length; i++) {
			HEX_INDEXES[HEX_DIGITS[i]] = i;
		}
	}

	@ToString
	public static String toPGString(final byte[] bA) {
		return null == bA ? null : quoteByteArray(bA);

	}

	@FromString
	public static byte[] fromPGString(final String bA) {
		return null == bA ? null : unquoteByteArray(bA);

	}

	public static String quoteByteArray(final byte[] bA) {
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

		return new String(buffer);
	}

	public static byte[] unquoteByteArray(final String value) {
		final int len = value.length();
		if ((len == 0) || (len == 2)) {
			return new byte[0];
		}

		final int newLen = ((len - 2) >> 1);
		final byte[] buffer = new byte[newLen];

		int index = 2;
		for (int i = 0; i < newLen; i++) {
			final char chH = value.charAt(index++);
			final char chL = value.charAt(index++);
			buffer[i] = (byte) ((HEX_INDEXES[chH] << 4) + HEX_INDEXES[chL]);
		}

		return buffer;
	}
}
