package hr.element.pgscala
package builder

object LongConverterBuilder extends SPredefConverterBuilder {
  val pgType = "bigint"

  val clazz = "scala.Long"
} 