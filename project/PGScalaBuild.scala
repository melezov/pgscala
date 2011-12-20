import sbt._
import Keys._

object Resolvers {
  val eleRepo = "Element Repo" at "http://maven.element.hr/nexus/content/groups/public"

  val resolverSettings = Seq(
    resolvers          := Seq(eleRepo),
    externalResolvers <<= resolvers map { rs =>
      Resolver.withDefaultResolvers(rs, mavenCentral = false, scalaTools = false)
    }
  )
}

object Publishing {
  val publishSettings = Seq(
    publishTo := Some("Element Nexus" at "http://maven.element.hr/nexus/content/repositories/releases/"),
    credentials += Credentials(Path.userHome / ".publish" / ".credentials"),
    publishArtifact in (Compile, packageDoc) := false
  )
}

object BuildSettings {
  import Resolvers._
  import Publishing._

  val commonSettings =
    Defaults.defaultSettings ++
    resolverSettings ++
    publishSettings ++ Seq(
      organization := "hr.element.pgscala",
      crossScalaVersions := Seq("2.9.1", "2.9.0-1", "2.9.0"),
      scalaVersion <<= (crossScalaVersions) { versions => versions.head },
      scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "UTF-8", "-optimise") // , "-Yrepl-sync"
    )

  val bsPGScalaUtil = commonSettings ++ Seq(
    name         := "PGScala-Util",
    version      := "0.1.4",

    javacOptions := Seq("-deprecation", "-encoding", "UTF-8", "-source", "1.5", "-target", "1.5"), // , "-g:none"),
    compileOrder := CompileOrder.JavaThenScala,
    autoScalaLibrary := false,
    crossPaths := false
  )

  val bsPGScalaConverters = commonSettings ++ Seq(
    name         := "PGScala-Converters",
    version      := "0.0.1"
  )

  val bsPGScala = commonSettings ++ Seq(
    name         := "PGScala",
    version      := "0.6.0"
  )
}

object Dependencies {
  val jodaConvert = "org.joda" % "joda-convert" % "1.1"  
  val jodaTime = "joda-time" % "joda-time" % "2.0"  
  
  val iorc = "hr.element.etb" %% "iorc" % "0.0.21" 

  val postgres = "postgresql" % "postgresql" % "9.1-901.jdbc4"
  
  val configrity = "org.streum" %% "configrity" % "0.9.0" % "test"
  val scalaTest = "org.scalatest" %% "scalatest" % "1.6.1" % "test"

  val depsPGScalaUtil = Seq(
    postgres % "test",
    configrity,
    scalaTest
  )

  val depsPGScalaConverters = Seq(
    jodaConvert,  
    jodaTime,    
    postgres % "test",
    configrity,
    scalaTest
  )
  
  val depsPGScala = Seq(
    iorc,
    postgres
  )
}

object PGScalaBuild extends Build {
  import BuildSettings._
  import Dependencies._

  lazy val pgscalaUtil = Project(
    "PGScala-Util",
    file("pgscala-util"),
    settings = bsPGScalaUtil ++ Seq(
      libraryDependencies := depsPGScalaUtil
    )
  )

  lazy val pgscalaConverters = Project(
    "PGScala-Converters",
    file("pgscala-converters"),
    settings = bsPGScalaConverters ++ Seq(
      libraryDependencies := depsPGScalaConverters
    )
  ) dependsOn(pgscalaUtil)

  lazy val pgscala = Project(
    "PGScala",
    file("pgscala"),
    settings = bsPGScala ++ Seq(
      libraryDependencies := depsPGScala
    )
  ) dependsOn(pgscalaConverters)
}
