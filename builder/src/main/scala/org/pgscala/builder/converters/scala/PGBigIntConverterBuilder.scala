package org.pgscala
package builder
package converters

object PGBigIntConverterBuilder
    extends PGPredefConverterBuilder {

  val scalaClazz = "scala.math.BigInt"

  override val javaClazz = "java.math.BigInteger"

  override val to =
    "%s.bigIntegerToString(bi.bigInteger)" format nullableConverter

  override val from =
    "new BigInt(%s.stringToBigInteger(bi))" format nullableConverter

  val defaultValue = "BigInt(0)"
}
