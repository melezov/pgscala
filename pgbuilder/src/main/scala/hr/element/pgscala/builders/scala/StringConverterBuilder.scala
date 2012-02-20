package hr.element.pgscala
package builder

object StringConverterBuilder extends SPredefConverterBuilder {
  val pgType = "text"

  val clazz = "java.lang.String"
  
  override def inject(body: String) = {
    val code = super.inject(body)

    code
      .replaceAll("""=\s*\n\s*PGNullableConverter.*""", "= s")
  }
} 