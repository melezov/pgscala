package hr.element.pgscala
package builder

object PGBuilder extends App {
  JConverterBuilder.buildJavaConverters()
}

//
//  def buildJavaConverters() {
//    val nullableTemplate =
//      Resource.fromClasspath("PGNullableConverter.java")
//        .slurpString(UTF8)
//
//    for (c <- converters) {
//      val path = Path("pgjava-converters") /
//        "src" / "main" / "java" /
//        "hr" / "element" / "pgscala" / "converters" /
//        ("PGNullable%sConverter.java" format c.fileName)
//
//      path.write(c.inject(nullableTemplate))(UTF8)
//    }
//  }
//
//  def buildJasminProxies() {
//    val nullableTemplate =
//      Resource.fromClasspath("PGNullableConverter.j")
//        .slurpString(UTF8)
//
//    val sB = new StringBuilder
//
//    for (c <- converters) {
//      val templateBody =
//        if (c ne converters.head) {
//          nullableTemplate.split('\n').drop(2).mkString("\n")
//        }
//        else nullableTemplate
//
//        sB ++= c.inject(templateBody)
//    }
//
//    val path = Path("pgscala-converters") /
//      "src" / "main" / "jasmin" /
//      "hr" / "element" / "pgscala" / "converters" /
//      "PGNullableConverter.j"
//
//    path.write(sB.toString)(UTF8)
//  }
//
//  def buildScalaConverters() {
//    val baseTemplate =
//      Resource.fromClasspath("PGConverter.scala")
//        .slurpString(UTF8)
//
//    for (c <- converters) {
//      val path = Path("pgscala-converters") /
//        "src" / "main" / "scala" /
//        "hr" / "element" / "pgscala" / "converters" /
//        ("PG%sConverter.scala" format c.fileName)
//
//      path.write(c.inject(baseTemplate))(UTF8)
//    }
//  }
//
//  buildJavaConverters()
//  buildJasminProxies()
//  buildScalaConverters()
//}
