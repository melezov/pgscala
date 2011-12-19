package hr.element.pgscala.util
package types

import org.joda.time._

object PGByteArrayConverter extends PGTypeConverter[Array[Byte]] {

  private val HexDigits =
    "0123456789abcdef".toCharArray

  private val HexIndexes = {
    val buf = new Array[Byte]('f' + 1)
    for (i <- 0 to 9) {
      buf('0' + i) = i.toByte
    }

    for (i <- 10 to 15) {
      buf('a' - 10 + i) = i.toByte
      buf('A' - 10 + i) = i.toByte
    }

    val len = revMap.map(_._1).max + 1
    revMap.foreach(r => buf(r._1) = r._2)
    buf
  }

  def toString(bA: Array[Byte]): String = {
    ""



  }

  def fromString(value: String): Array[Byte] = {
    null
  }
}
