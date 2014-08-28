package org.pgscala
package builder
package converters

object PGBigDecimalConverterBuilder
    extends PGPredefConverterBuilder {

  val scalaClazz = "scala.math.BigDecimal"

  override val javaClazz = "java.math.BigDecimal"

  override val to = """ =
    PGNullableConverter.toPGString(bD.bigDecimal)"""

  override val from = """
    new BigDecimal(PGNullableConverter.fromPGString(bD))"""

  override val defaultValue = """BigDecimal(0)"""
}
