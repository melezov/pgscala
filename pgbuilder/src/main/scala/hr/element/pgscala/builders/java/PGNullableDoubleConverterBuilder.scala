package hr.element.pgscala
package builder

object PGNullableDoubleConverterBuilder extends PGPredefNullableConverterBuilder {
  val pgType = "double precision"

  val clazz = "java.lang.Double"

  val to = "Double.toString(d)"

  val from = "Double.valueOf(d)"
}
