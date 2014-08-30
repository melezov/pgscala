package org.pgscala
package builder
package converters

object PGLocalDateConverterBuilder
    extends PGConverterBuilder {

  override val imports = """
import org.joda.time.LocalDate
"""

  val scalaClazz = "org.joda.time.LocalDate"

  val defaultValue = """LocalDate.parse("0001-01-01")"""
}
