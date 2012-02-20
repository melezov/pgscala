package hr.element.pgscala
package builder

object JBigDecimalConverterBuilder extends JConverterBuilder {
  override val imports = "import java.math.BigInteger;"

  val pgType = "decimal"

  val clazz = "java.math.BigDecimal"

  val to = "bD.toString()"

  val from = "new BigDecimal(bD)"
}
