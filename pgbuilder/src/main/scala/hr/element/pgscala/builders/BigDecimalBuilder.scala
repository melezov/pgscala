package hr.element.pgscala
package builders

object BigDecimalBuilder extends Builder {
  val clazz = "java.math.BigDecimal"

  val pgType = "decimal"

  val to = "bD.toString()"

  val from = "new BigDecimal(bD)"
}
