package hr.element.pgscala.converters

import org.joda.time._

object PGDateTimeConverter extends PGTypeConverter[DateTime] {
  //TODO convert to postgres default
  def toString(dT: DateTime): String =
    dT.toString()/*("yyyy-MM-dd HH:mm:ss.ffffffK")*/

  //TODO HACK... fix as soon as possible
  def fromString(value: String): DateTime =
    DateTime.parse(value.replace(' ', 'T'))
}
