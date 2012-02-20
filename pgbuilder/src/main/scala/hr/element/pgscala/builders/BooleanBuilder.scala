package hr.element.pgscala
package builders

object BooleanBuilder extends Builder {
  val clazz = "java.lang.Boolean"

  val pgType = "boolean"

  val to = """b ? "t" : "f""""

  val from = """b.equals("t") ? Boolean.TRUE : Boolean.FALSE"""

  override def javaImports = ""
}
