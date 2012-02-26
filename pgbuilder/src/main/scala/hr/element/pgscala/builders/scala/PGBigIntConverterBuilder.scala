package hr.element.pgscala
package builder

object PGBigIntConverterBuilder extends PGPredefConverterBuilder {
  val scalaClazz = "scala.math.BigInt"

  override val javaClazz = "java.math.BigInteger"

  override val to = """ =
    PGNullableConverter.toPGString(bI.bigInteger)"""

  override val from = """ =
    new BigInt(PGNullableConverter.fromPGString(bI))"""
}