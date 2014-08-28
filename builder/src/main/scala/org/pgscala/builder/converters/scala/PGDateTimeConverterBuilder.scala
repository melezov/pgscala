package org.pgscala
package builder
package converters

object PGDateTimeConverterBuilder
    extends PGConverterBuilder {

  val scalaClazz = "org.joda.time.DateTime"

  override val defaultValue = """DateTime.parse("0001-01-01T00:00:00Z")"""
}
