package hr.element.pgscala
package builders

object BigIntegerBuilder extends Builder {
  val clazz = "java.math.BigInteger"

  val pgType = "numeric"

  val to = "bI.toString()"

  val from = "new BigInteger(bI)"
}
