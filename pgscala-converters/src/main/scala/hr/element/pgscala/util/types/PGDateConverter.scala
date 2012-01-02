package hr.element.pgscala.util
package types

import org.joda.time.LocalDate

object PGNullableDateConverter extends PGTypeConverter[Option[LocalDate]] {
  def toString(lD: Option[LocalDate]): String =
    lD.toString

  def fromString(value: String): Option[LocalDate] =
    LocalDate.parse(value)
}
