package hr.element.pgscala
package builders

object ShortBuilder extends Builder {
  val clazz = "java.lang.Short"

  val pgType = "smallint"

  val to = "Short.toString(s)"

  val from = "Short.valueOf(s)"

  override def javaImports = ""
}
