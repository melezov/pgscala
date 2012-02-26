package hr.element.pgscala
package builder

object PGNullableIntegerConverterBuilder extends PGPredefNullableConverterBuilder {
  val pgType = "integer"

  val clazz = "java.lang.Integer"

  val to = "Integer.toString(i)"

  val from = "Integer.valueOf(i)"
}
