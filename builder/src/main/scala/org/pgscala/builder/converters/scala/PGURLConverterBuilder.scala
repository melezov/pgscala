package org.pgscala
package builder
package converters

object PGURLConverterBuilder
    extends PGConverterBuilder {

  val scalaClazz = "URL"

  override val imports = """
import java.net.URL
"""

  val defaultValue = """null"""
}
