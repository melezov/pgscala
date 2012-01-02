package hr.element.pgscala.util
package types

import org.joda.time._

object PGDateTimeConverter extends PGTypeConverter[DateTime] {
  def toString(dT: DateTime): String =
    "\"" + dT.toString("yyyy-MM-dd HH:mm:ss.ffffffK") + "\""

  //TODO HACK... fix as soon as possible
  def fromString(value: String): DateTime =
    DateTime.parse(value.replace(' ', 'T'))
}
