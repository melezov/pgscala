package org.pgscala
package builder
package converters

object PGNullableDateTimeConverterBuilder extends PGNullableConverterBuilder {
  override val imports = """
import org.joda.time.DateTime;
import org.joda.time.format.*;
"""

  val pgType = "timestamptz"

  val clazz = "org.joda.time.DateTime"

  override val body =
"""  private static final DateTimeFormatter dateTimeFormat =
    DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSSSSZZ");

  private static final DateTimeFormatter dateTimeSecondFormat =
    DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ssZZ");
"""

  val to = "dateTimeFormat.print(dt)"

  val from = """try {
      return dateTimeFormat.parseDateTime(dt);
    }
    catch(final IllegalArgumentException e) {
      return dateTimeSecondFormat.parseDateTime(dt);
    }"""

  override def inject(body: String) = {
    val code = super.inject(body)
    code
      .replaceFirst("return null == dt \\? null : try", """if (null == dt) return null;
    try""").replaceFirst("\\};", "}")
  }
}
