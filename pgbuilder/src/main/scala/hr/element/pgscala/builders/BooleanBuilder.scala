package hr.element.pgscala
package builders

object BooleanBuilder extends Builder {
  val clazz = "Boolean"

  val pgType = "boolean"

  val to = """b ? "t" : "f""""

  val from = """b.equals("t") ? Boolean.TRUE : Boolean.FALSE"""
}
