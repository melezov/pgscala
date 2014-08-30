package org.pgscala
package builder
package converters

object PGNullableLocalDateConverterBuilder extends PGNullableConverterBuilder {
  override val imports = """
import org.joda.time.LocalDate;
"""

  val clazz = "org.joda.time.LocalDate"

  val pgType = "date"

  val to = "ld.toString()"

  val from = "new LocalDate(ld)"
}
