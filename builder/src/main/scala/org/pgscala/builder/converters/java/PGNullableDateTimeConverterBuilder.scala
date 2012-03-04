package org.pgscala
package builder
package converters

object PGNullableDateTimeConverterBuilder extends PGNullableConverterBuilder {
  override val imports = """import org.joda.time.DateTime;
import org.joda.time.format.*;"""

  val pgType = "timestamptz"

  val clazz = "org.joda.time.DateTime"

  override val body =
"""  private static final DateTimeFormatter dateTimeFormat =
    DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSSSSZZ");
"""

  val to = "dateTimeFormat.print(dT)"

  val from = "dateTimeFormat.parseDateTime(dT)"
}
