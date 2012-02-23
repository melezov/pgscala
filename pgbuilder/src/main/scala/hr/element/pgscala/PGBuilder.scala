package hr.element.pgscala
package builder

object PGBuilder extends App {
  JConverterBuilder.buildJavaNullableConverters()
  JConverterBuilder.buildJasminProxy()
  SConverterBuilder.buildScalaConverters()
}
