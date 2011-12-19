package hr.element.pgscala.util
package types

import org.joda.time._

object PGDateConverter extends PGTypeConverter[LocalDate] {
  def toString(lD: LocalDate): String = {
    lD.toString();
  }

  def fromString(value: String): LocalDate = {
    return LocalDate.parse(value);
  }
}
