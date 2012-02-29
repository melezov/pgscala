package org.pgscala
package builder
package converters

object PGStringConverterBuilder extends PGPredefConverterBuilder {
  val scalaClazz = "java.lang.String"

  override def inject(body: String) = {
    val code = super.inject(body)

    code.replaceAll("""=\s*\n\s*PGNullableConverter.*""", "= s")
  }
}
