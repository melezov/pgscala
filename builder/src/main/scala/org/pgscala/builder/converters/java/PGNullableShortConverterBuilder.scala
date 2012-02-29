package org.pgscala
package builder
package converters

object PGNullableShortConverterBuilder extends PGPredefNullableConverterBuilder {
  val pgType = "smallint"

  val clazz = "java.lang.Short"

  val to = "Short.toString(s)"

  val from = "Short.valueOf(s)"
}
