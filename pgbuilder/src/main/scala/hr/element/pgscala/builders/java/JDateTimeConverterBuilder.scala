package hr.element.pgscala
package builder

object JDateTimeConverterBuilder extends JConverterBuilder {
  override val imports = """import org.joda.time.DateTime;
import org.joda.time.format.*;"""

  val pgType = "timestamptz"

  val clazz = "org.joda.time.DateTime"

  override val body =
"""  private static final DateTimeFormatter dateTimeFormat =
    DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSZZ");
"""

  val to = "dateTimeFormat.print(dT)"

  val from = "dateTimeFormat.parseDateTime(dT)"
}
