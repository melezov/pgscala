package hr.element.pgscala.util
package types

import org.joda.time._

object PGByteArrayConverter extends PGTypeConverter[Array[Byte]] {

  private val HexDigits =
    "0123456789abcdef".toCharArray

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

  def fromString(value: String): Array[Byte] = {
    null
  }
}
