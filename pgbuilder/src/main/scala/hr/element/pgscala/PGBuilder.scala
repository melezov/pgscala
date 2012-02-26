package hr.element.pgscala
package builder

object PGBuilder extends App {
  PGNullableConverterBuilder.buildJavaNullableConverters()
  PGNullableConverterBuilder.buildJasminProxy()
  PGConverterBuilder.buildScalaConverters()
  PGConverterBuilder.buildScalaOptionConverters()
  PGConverterBuilder.buildScalaConverterImplicits()
}
