package org.pgscala
package builder
package converters

object PGNullableBigDecimalConverterBuilder extends PGNullableConverterBuilder {
  override val imports = "import java.math.BigDecimal;"

  val pgType = "decimal"

  val clazz = "java.math.BigDecimal"

  val to = "bD.toString()"

  val from = "new BigDecimal(bD)"
}
