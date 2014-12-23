package org.pgscala
package builder
package converters

object PGNullablePointConverterBuilder extends PGPredefNullableConverterBuilder {
  val pgType = "bigint"

  val clazz = "java.lang.Long"

  val to = "Long.toString(l)"

  val from = "Long.valueOf(l)"
}
