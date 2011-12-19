package hr.element.pgscala.util
package types

import org.joda.time._

object PGByteArrayConverter extends PGTypeConverter[Array[Byte]] {
  def toString(bA: Array[Byte]): String = {
    ""
  }

  def fromString(value: String): Array[Byte] = {
    null
  }
}
