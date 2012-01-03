package hr.element.pgscala.converters
package types

import org.joda.time.LocalDate

object PGDateConverter extends PGTypeConverter[LocalDate] {
  def toString(lD: LocalDate): String =
    lD.toString("yyyy-MM-dd")

  def fromString(value: String): LocalDate =
    LocalDate.parse(value)
}
