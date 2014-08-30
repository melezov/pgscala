package org.pgscala
package builder
package converters

object PGDateTimeConverterBuilder
    extends PGConverterBuilder {

  override val imports = """
import org.joda.time.DateTime
"""

  val scalaClazz = "org.joda.time.DateTime"

  val defaultValue = """DateTime.parse("0001-01-01T00:00:00Z")"""
}
