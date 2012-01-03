object ClassBuilder extends App {
  val tpl = """
package hr.element.pgscala.util
package types

object ($obj) extends PGTypeConverter[($class)] {
  def toString(($ch): ($class)): String =
    ($ch).toString()

  def fromString(value: String): ($class) =
    java.lang.($name).parse($class)(value)
}
"""

  val dataTypes = Seq(
    "Integer",
    "Short",
    "Double",
    "Float",
    "Boolean",
    "Long"
  )

  dataTypes.foreach{ name =>

  val clazz = name.replace("Integer", "Int")

  val ch = clazz.head.toLower.toString
  val obj = "PG%sConverter" format(name)

  val body = tpl
    .replace("($obj)", obj)
    .replace("($name)", name)
    .replace("($class)", clazz)
    .replace("($ch)", ch)

    val fOS = new java.io.FileOutputStream(obj+".scala")
    fOS.write(body.getBytes("UTF-8"))
    fOS.close()
  }
}
