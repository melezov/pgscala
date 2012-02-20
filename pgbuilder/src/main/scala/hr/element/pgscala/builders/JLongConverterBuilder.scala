package hr.element.pgscala
package builder

object JLongConverterBuilder extends JPredefConverterBuilder {
  val pgType = "bigint"

  val clazz = "java.lang.Long"

  val to = "Long.toString(l)"

  val from = "Long.valueOf(l)"
}
