package org.pgscala
package builder

import converters._

object PGBuilder extends App {
  PGNullableConverterBuilder.buildJavaNullableConverters()
  PGNullableConverterBuilder.buildJasminProxy()

  PGConverterBuilder.buildScalaConverters()
  PGConverterBuilder.buildScalaOptionConverters()
  PGConverterBuilder.buildScalaConverterImplicits()
}
