package hr.element.pgscala
package builder

object JShortConverterBuilder extends JConverterBuilder {
  val pgType = "smallint"

  val clazz = "java.lang.Short"

  val to = "Short.toString(s)"

  val from = "Short.valueOf(s)"
}
