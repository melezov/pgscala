package org.pgscala
package builder
package converters

object PGNullableBigIntegerConverterBuilder extends PGNullableConverterBuilder {
  override val imports = """
import java.math.BigInteger;
"""

  val pgType = "numeric"

  val clazz = "java.math.BigInteger"

  val to = "bi.toString()"

  val from = "new BigInteger(bi)"
}
