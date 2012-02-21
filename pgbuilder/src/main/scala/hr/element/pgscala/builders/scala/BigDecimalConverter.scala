package hr.element.pgscala
package builder

object BigDecimalConverterBuilder extends SPredefConverterBuilder {
  val scalaClazz = "scala.math.BigDecimal"

  override val javaClazz = "java.math.BigDecimal"

  override val to = """ =
    PGNullableConverter.toPGString(bD.bigDecimal)"""

  override val from = """ =
    new BigDecimal(PGNullableConverter.fromPGString(bD))"""
}