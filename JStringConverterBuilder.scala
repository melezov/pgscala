package hr.element.pgscala
package builders

object JStringConverterBuilder extends JConverterBuilder {
  val pgType = "text"

  val clazz = "java.lang.String"

  val to = "s"

  val from = "s"
/*
  override def inject(body: String) = {
    val code = super.inject(body)

    code
      .replaceFirst("""(?s)(  @ToString\n).*(  @FromString)""", "$1$2")
      .replaceAll("return (?:null|string).*", "return s;")
      .replaceFirst("""(?s)\n\.method.*?\.end method\n""", "")
      .replaceAll("""\s+PGNullableConverter\..*""", " s")
  }
*/
}