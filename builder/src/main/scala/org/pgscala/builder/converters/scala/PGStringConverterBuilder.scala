package org.pgscala
package builder
package converters

object PGStringConverterBuilder
    extends PGPredefConverterBuilder {

  val scalaClazz = "java.lang.String"

  override def inject(body: String) = {
    val code = super.inject(body)

    code.replaceFirst("""=\n\s+PGNullableStringConverter.*""", "= s")
  }

  val defaultValue = "\"\""

  override def from = "s"
}
