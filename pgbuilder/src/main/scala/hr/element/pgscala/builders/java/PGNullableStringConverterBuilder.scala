package hr.element.pgscala
package builder

object PGNullableStringConverterBuilder extends PGPredefNullableConverterBuilder {
  val pgType = "text"

  val clazz = "java.lang.String"

  val to = "s"

  val from = "s"

  override def inject(body: String) = {
    val code = super.inject(body)

    code
      .replaceFirst("""(?s)(  @ToString\n).*(  @FromString)""", "$1$2")
      .replaceAll("return.*", "return s;")
  }
}
