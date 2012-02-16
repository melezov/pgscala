package hr.element.pgscala
package builders

object DateTimeBuilder extends Builder {
  override def imports = "import org.joda.time.format.*;"

  override def pre =
"""  private static final DateTimeFormatter dateTimeFormat =
    DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSZZ");
"""

  def clazz = "org.joda.time.DateTime"

  def pgType = "timestamptz"

  def to = "dateTimeFormat.print(dT)"

  def from = "dateTimeFormat.parseDateTime(dT)"
}
