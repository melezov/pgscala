import sbt._
import Keys._

object BuildSettings {
  import Default._

//  ###########################################################################

  val bsUtil = javaSettings ++ Seq(
    name    := "pgscala-util"
  , version := "0.3.2"
  , initialCommands := "import org.pgscala.util._"
  )

  val bsIORC = scalaSettings ++ Seq(
    name    := "pgscala-iorc"
  , version := "0.1.2"
  , initialCommands := "import org.pgscala.iorc._"
  )

//  ===========================================================================

  val proxy = TaskKey[Unit]("proxy", "Compiles jasmin proxy methods (return type overloading for Scala)")
  val proxyTask = proxy <<= (baseDirectory, scalaVersion) map { (bD, sV) =>
    val src = bD / ".."  / "converters-scala" /
      "src" / "main" / "jasmin" / "org" / "pgscala" / "converters" / "PGNullableConverter.j"

    val dst = bD / ".."  / "converters-scala" /
      "target" / ("scala-%s" format sV) / "classes"

    jasmin.Main.main(Array(src.absolutePath, "-d", dst.absolutePath))
  }

  val bsBuilder = scalaSettings ++ Seq(
    name    := "builder"
  , version := "0.0.0-SNAPSHOT"
  , proxyTask
  )

  //  ---------------------------------------------------------------------------

  val bsConvertersJava = javaSettings ++ Seq(
    name    := "pgscala-converters-java"
  , version := "0.2.7"
  , initialCommands := "import org.pgscala.converters._"
  )

  val bsConvertersScala = scalaSettings ++ Seq(
    name    := "pgscala-converters-scala"
  , version := "0.2.7"
  , initialCommands := "import org.pgscala.converters._"
  , unmanagedSourceDirectories in Compile <<= (scalaSource in Compile, javaSource in Compile)(_ :: _ :: Nil)
  )

  val bsPGScala = scalaSettings ++ Seq(
    name    := "pgscala"
  , version := "0.7.12"
  , initialCommands := "import org.pgscala._"
  )

  val bsPool = scalaSettings ++ Seq(
    name    := "pgscala-pool"
  , version := "0.2.7"
  )
}

//  ---------------------------------------------------------------------------

trait Publications {
  val pgscalaUtil            = "org.pgscala" %  "pgscala-util"             % "0.3.2"
  val pgscalaIORC            = "org.pgscala" %% "pgscala-iorc"             % "0.1.2"
  val pgscalaConvertersJava  = "org.pgscala" %  "pgscala-converters-java"  % "0.2.7"
  val pgscalaConvertersScala = "org.pgscala" %% "pgscala-converters-scala" % "0.2.7"
  val pgscala                = "org.pgscala" %% "pgscala"                  % "0.7.12"
}

//  ---------------------------------------------------------------------------

object Dependencies extends Publications {
  val jodaTime = Seq(
    "org.joda" % "joda-convert" % "1.2"
  , "joda-time" % "joda-time" % "2.1"
  )

  val scalaIo = "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.0"

  val postgres = "postgresql" % "postgresql" % "9.1-902.jdbc4"

  val slf4j = "org.slf4j" % "slf4j-api" % "1.6.6"
  val log4jOverSlf4j = "org.slf4j" % "log4j-over-slf4j" % "1.6.6"

  val logback = "ch.qos.logback" % "logback-classic" % "1.0.6"

  val c3p0 = "c3p0" % "c3p0" % "0.9.1.2"

  val scalaTest = "org.scalatest" %% "scalatest" % "2.0.M4" % "test"
}

//  ---------------------------------------------------------------------------

import Implicits._

object ProjectDeps {
  import Dependencies._

  val depsUtil = libDeps(
    slf4j
  , scalaTest
  )

  val depsIORC = libDeps(
    scalaTest
  )

  val depsBuilder = libDeps(
    scalaIo
  )

  val depsConvertersJava = libDeps(
    jodaTime
  , scalaTest
  )

  val depsConvertersScala = libDeps(
    pgscalaConvertersJava
  , scalaTest
  )

  val depsPGScala = libDeps(
    postgres
  , slf4j
  , pgscalaUtil
  , pgscalaIORC
  , pgscalaConvertersScala
  , scalaTest
  , logback % "test"
  )

  val depsPool = libDeps(
    c3p0
  , log4jOverSlf4j
  , pgscala
  , scalaTest
  , logback % "test"
  )
}

//  ###########################################################################

object PGScalaBuild extends Build {
  import BuildSettings._
  import ProjectDeps._

  lazy val util = Project(
    "util",
    file("util"),
    settings = bsUtil :+ depsUtil
  )

  lazy val iorc = Project(
    "iorc",
    file("iorc"),
    settings = bsIORC :+ depsIORC
  )

  lazy val builder = Project(
    "builder",
    file("builder"),
    settings = bsBuilder :+ depsBuilder
  )

  lazy val pgjavaConverters = Project(
    "converters-java",
    file("converters-java"),
    settings = bsConvertersJava :+ depsConvertersJava
  )

  lazy val pgscalaConverters = Project(
    "converters-scala",
    file("converters-scala"),
    settings = bsConvertersScala :+ depsConvertersScala
  ) // dependsOn(pgjavaConverters)

  lazy val pgscala = Project(
    "pgscala",
    file("pgscala"),
    settings = bsPGScala :+ depsPGScala
  ) // dependsOn(util, iorc, pgscalaConverters)

  lazy val pgscalaPool = Project(
    "pool",
    file("pool"),
    settings = bsPool :+ depsPool
  ) // dependsOn(pgscala)
}

//  ---------------------------------------------------------------------------

object Repositories {
  val ElementNexus     = "Element Nexus"     at "http://maven.element.hr/nexus/content/groups/public/"
  val ElementReleases  = "Element Releases"  at "http://maven.element.hr/nexus/content/repositories/releases/"
  val ElementSnapshots = "Element Snapshots" at "http://maven.element.hr/nexus/content/repositories/snapshots/"
}

//  ---------------------------------------------------------------------------

object Resolvers {
  import Repositories._

  val settings = Seq(
    resolvers := Seq(ElementNexus, ElementReleases, ElementSnapshots),
    externalResolvers <<= resolvers map { rS =>
      Resolver.withDefaultResolvers(rS, mavenCentral = false)
    }
  )
}

//  ---------------------------------------------------------------------------

object Publishing {
  import Repositories._

  val settings = Seq(
    publishTo <<= (version) { version => Some(
      if (version.endsWith("SNAPSHOT")) ElementSnapshots else ElementReleases
    )}
  , credentials += Credentials(Path.userHome / ".config" / "pgscala" / "nexus.config")
  , publishArtifact in (Compile, packageDoc) := false
  )
}

//  ---------------------------------------------------------------------------

object Default {
  val scalaSettings =
    Defaults.defaultSettings ++
    Resolvers.settings ++
    Publishing.settings ++ Seq(
      organization := "org.pgscala"
    , crossScalaVersions := Seq("2.9.2", "2.9.1-1", "2.9.1", "2.9.0-1", "2.9.0")
    , scalaVersion <<= (crossScalaVersions) { versions => versions.head }
    , scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "UTF-8", "-optimise")
    , unmanagedSourceDirectories in Compile <<= (scalaSource in Compile)(_ :: Nil)
    , unmanagedSourceDirectories in Test    <<= (scalaSource in Test   )(_ :: Nil)
    )

  val javaSettings =
    scalaSettings ++ Seq(
      autoScalaLibrary := false
    , crossPaths := false
    , javacOptions := Seq("-deprecation", "-encoding", "UTF-8", "-source", "1.5", "-target", "1.5")
    , unmanagedSourceDirectories in Compile <<= (javaSource in Compile)(_ :: Nil)
    )
}

//  ---------------------------------------------------------------------------

object Implicits {
  implicit def depToSeq(m: ModuleID) = Seq(m)

  def libDeps(deps: (Seq[ModuleID])*) =
    libraryDependencies ++= deps.flatten
}
