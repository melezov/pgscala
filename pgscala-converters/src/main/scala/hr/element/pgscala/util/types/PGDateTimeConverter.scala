package hr.element.pgscala.util
package types

import org.joda.time.DateTime

object PGDateTimeConverter extends PGTypeConverter[DateTime] {
  def toString(dT: DateTime): String =
    dT.toString

  def fromString(value: String): DateTime =
    DateTime.parse(value)
}
