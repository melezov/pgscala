import sbt._
import Keys._

object Repositories {
  val ElementNexus     = "Element Nexus"     at "http://maven.element.hr/nexus/content/groups/public/"
  val ElementReleases  = "Element Releases"  at "http://maven.element.hr/nexus/content/repositories/releases/"
  val ElementSnapshots = "Element Snapshots" at "http://maven.element.hr/nexus/content/repositories/snapshots/"
}

object Resolvers {
  import Repositories._

  val resolverSettings = Seq(
    resolvers := Seq(ElementNexus, ElementReleases, ElementSnapshots),
    externalResolvers <<= resolvers map { rs =>
      Resolver.withDefaultResolvers(rs, mavenCentral = false, scalaTools = false)
    }
  )
}

object Publishing {
  import Repositories._
  import Resolvers._

  val publishSettings = Seq(
    publishTo <<= (version) { version => Some(
      if (version.endsWith("SNAPSHOT")) ElementSnapshots else ElementReleases
    )},
    credentials += Credentials(Path.userHome / ".publish" / "element.credentials"),
    publishArtifact in (Compile, packageDoc) := false
  )
}

//  ---------------------------------------------------------------------------

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

//  ---------------------------------------------------------------------------

  val bsPGScalaUtil = commonSettings ++ Seq(
    name         := "PGScala-Util",
    version      := "0.2.1-SNAPSHOT",

    javacOptions := Seq("-deprecation", "-encoding", "UTF-8", "-source", "1.5", "-target", "1.5"), // , "-g:none"),
    compileOrder := CompileOrder.JavaThenScala,
    autoScalaLibrary := false,
    crossPaths := false
  )

  val bsPGScalaConverters = commonSettings ++ Seq(
    name         := "PGScala-Converters",
    version      := "0.0.2-SNAPSHOT"
  )

  val bsPGScala = commonSettings ++ Seq(
    name         := "PGScala",
    version      := "0.6.1-SNAPSHOT"
  )
}

//  ---------------------------------------------------------------------------

object Dependencies {
  val jodaConvert = "org.joda" % "joda-convert" % "1.1"
  val jodaTime = "joda-time" % "joda-time" % "2.0"

  val iorc = "hr.element.etb" %% "iorc" % "0.0.21"

  val postgres = "postgresql" % "postgresql" % "9.1-901.jdbc4"

  val configrity = "org.streum" % "configrity_2.9.1" % "0.9.0" % "test"  //    scalaVersion.replace("2.9.0", "2.9.0-1") % "0.9.0" % "test"
  val scalaTest = "org.scalatest" %% "scalatest" % "1.6.1" % "test"

//  ---------------------------------------------------------------------------

  val pgscalaUtil = "hr.element.pgscala" % "pgscala-util" % "0.2.0"

//  ---------------------------------------------------------------------------

  val depsPGScalaUtil = Seq(
    //test
    postgres % "test",
    configrity,
    scalaTest
  )

  val depsPGScalaConverters = Seq(
    pgscalaUtil,
    jodaConvert,
    jodaTime,

    //test
    postgres % "test",
    configrity,
    scalaTest
  )

  val depsPGScala = Seq(
    pgscalaUtil,
    jodaConvert,
    jodaTime,
    iorc,
    postgres,

    //test
    configrity,
    scalaTest
  )
}

//  ---------------------------------------------------------------------------

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
  )

  lazy val pgscala = Project(
    "PGScala",
    file("pgscala"),
    settings = bsPGScala ++ Seq(
      libraryDependencies := depsPGScala
    )
  )
}
