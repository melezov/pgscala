package hr.element.pgscala
package builders

object LongBuilder extends Builder {
  val clazz = "java.lang.Long"

  val pgType = "bigint"

  val to = "Long.toString(l)"

  val from = "Long.valueOf(l)"
}
