package org.pgscala
package builder
package converters

object PGLocalDateConverterBuilder
    extends PGConverterBuilder {

  val scalaClazz = "org.joda.time.LocalDate"

  override val defaultValue = """LocalDate.parse("0001-01-01")"""
}
