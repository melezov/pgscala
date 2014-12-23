package org.pgscala
package builder
package converters

object PGNullableURLConverterBuilder extends PGNullableConverterBuilder {
  override val imports = """
import java.net.URL;
import java.net.MalformedURLException;
"""

  val pgType = "varchar"

  val clazz = "java.net.URL"

  val to = "url.toString()"

  val from = "new URL(url)"

  override val fromThrowsExceptions = Seq("MalformedURLException")
}
