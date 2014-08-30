package org.pgscala
package builder
package converters

object PGNullableBigDecimalConverterBuilder extends PGNullableConverterBuilder {
  override val imports = """
import java.math.BigDecimal;
"""

  val pgType = "decimal"

  val clazz = "java.math.BigDecimal"

  val to = "bd.toString()"

  val from = "new BigDecimal(bd)"
}
