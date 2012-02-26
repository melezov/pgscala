package hr.element.pgscala
package builder

object PGStringConverterBuilder extends PGPredefConverterBuilder {
  val scalaClazz = "java.lang.String"

  override def inject(body: String) = {
    val code = super.inject(body)

    code.replaceAll("""=\s*\n\s*PGNullableConverter.*""", "= s")
  }
}