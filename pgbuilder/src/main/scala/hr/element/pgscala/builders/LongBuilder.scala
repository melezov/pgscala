package hr.element.pgscala
package builders

object LongBuilder extends Builder {
  val clazz = "Long"

  val pgType = "bigint"

  val to = "Long.toString(l)"

  val from = "Long.valueOf(l)"
}
