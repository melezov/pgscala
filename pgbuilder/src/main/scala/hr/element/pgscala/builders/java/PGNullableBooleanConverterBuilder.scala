package hr.element.pgscala
package builder

object PGNullableBooleanConverterBuilder extends PGPredefNullableConverterBuilder {
  val pgType = "boolean"

  val clazz = "java.lang.Boolean"

  val to = """b ? "t" : "f""""

  val from = """b.equals("t") ? Boolean.TRUE : Boolean.FALSE"""
}
