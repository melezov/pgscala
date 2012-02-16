package hr.element.pgscala
package builders

object LocalDateBuilder extends Builder {
  def clazz = "org.joda.time.LocalDate"

  def pgType = "date"

  def to = "lD.toString()"

  def from = "new LocalDate(lD)"
}
