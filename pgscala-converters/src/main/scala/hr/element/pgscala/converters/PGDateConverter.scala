package hr.element.pgscala.converters

import org.joda.time.LocalDate

object PGDateConverter extends PGTypeConverter[LocalDate] {
  def toPGString(lD: LocalDate): String =
    lD.toString("yyyy-MM-dd")

  def fromPGString(value: String): LocalDate =
    LocalDate.parse(value)

  override val PGType = java.sql.Types.DATE
}
