package hr.element.pgscala
package builders

object StringBuilder extends Builder {
  val clazz = "String"

  val pgType = "text"

  val to = "s"

  val from = "s"

  override def inject(body: String) = {
    val code = super.inject(body)

    code
      .replaceFirst("""(?s)(  @ToString\n).*(  @FromString)""", "$1$2")
      .replaceAll("return.*", "return s;")
  }
}