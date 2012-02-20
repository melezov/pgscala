package hr.element.pgscala
package builder

object JLocalDateConverterBuilder extends JConverterBuilder {
  override val imports = "import org.joda.time.LocalDate;"

  val clazz = "org.joda.time.LocalDate"

  val pgType = "date"

  val to = "lD.toString()"

  val from = "new LocalDate(lD)"
}
