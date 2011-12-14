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
    credentials += Credentials(Path.userHome / ".publish" / ".credentials")
  )
}

object BuildSettings {
  import Resolvers._
  import Publishing._

  val commonSettings =
    Defaults.defaultSettings ++
    resolverSettings ++
    publishSettings

  val bsPGScalaUtil = commonSettings ++ Seq(
    organization := "hr.element.pgscala",
    name         := "PGScala-Util",
    version      := "0.1.4",

    javacOptions := Seq("-deprecation", "-encoding", "UTF-8", "-source", "1.5", "-target", "1.5", "-g:none"),
    compileOrder := CompileOrder.JavaThenScala,
    publishArtifact in (Compile, packageDoc) := false,
    autoScalaLibrary := false,
    crossPaths := false
  )
}

object Dependencies {
  val configrity = "org.streum" %% "configrity" % "0.9.0" % "test"
  val scalaTest = "org.scalatest" %% "scalatest" % "1.6.1" % "test"

  val depsPGScalaUtil = Seq(
    configrity,
    scalaTest
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
}
