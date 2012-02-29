package org.pgscala
package builder
package converters

object PGNullableIntegerConverterBuilder extends PGPredefNullableConverterBuilder {
  val pgType = "integer"

  val clazz = "java.lang.Integer"

  val to = "Integer.toString(i)"

  val from = "Integer.valueOf(i)"
}
