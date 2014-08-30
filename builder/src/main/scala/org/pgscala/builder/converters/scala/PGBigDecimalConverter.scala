package org.pgscala
package builder
package converters

object PGBigDecimalConverterBuilder
    extends PGConverterBuilder {

  val scalaClazz = "scala.math.BigDecimal"

  override val to =
    "%s.bigDecimalToString(bd.bigDecimal)" format nullableConverter

  override val from =
    "BigDecimal(%s.stringToBigDecimal(bd))" format nullableConverter

  val defaultValue = "BigDecimal(0)"
}
