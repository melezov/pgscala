package hr.element.pgscala
package builder

object PGNullableBigIntegerConverterBuilder extends PGNullableConverterBuilder {
  override val imports = "import java.math.BigInteger;"

  val pgType = "numeric"

  val clazz = "java.math.BigInteger"

  val to = "bI.toString()"

  val from = "new BigInteger(bI)"
}
