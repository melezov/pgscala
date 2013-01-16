import sbt._
import Keys._

object BuildSettings {
  import Default._

//  ###########################################################################

  val bsUtil = javaSettings ++ Seq(
    name    := "pgscala-util"
  , version := "0.3.5"
  , initialCommands := "import org.pgscala.util._"
  )

  val bsIORC = scalaSettings ++ Seq(
    name    := "pgscala-iorc"
  , version := "0.1.4"
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
  , version := "0.2.8"
  , initialCommands := "import org.pgscala.converters._"
  )

  val bsConvertersScala = scalaSettings ++ Seq(
    name    := "pgscala-converters-scala"
  , version := "0.2.12"
  , initialCommands := "import org.pgscala.converters._"
  , unmanagedSourceDirectories in Compile <<= (scalaSource in Compile, javaSource in Compile)(_ :: _ :: Nil)
  )

  val bsPGScala = scalaSettings ++ Seq(
    name    := "pgscala"
  , version := "0.7.16"
  , initialCommands := "import org.pgscala._"
  )

  val bsPool = scalaSettings ++ Seq(
    name    := "pgscala-pool"
  , version := "0.2.12"
  )
}

//  ---------------------------------------------------------------------------

trait Publications {
  val pgscalaUtil            = "org.pgscala" %  "pgscala-util"             % "0.3.5"
  val pgscalaIORC            = "org.pgscala" %% "pgscala-iorc"             % "0.1.4"
  val pgscalaConvertersJava  = "org.pgscala" %  "pgscala-converters-java"  % "0.2.8"
  val pgscalaConvertersScala = "org.pgscala" %% "pgscala-converters-scala" % "0.2.12"
  val pgscala                = "org.pgscala" %% "pgscala"                  % "0.7.16"
}

//  ---------------------------------------------------------------------------

trait Dependencies extends Publications {
  lazy val jodaTime = "joda-time" % "joda-time" % "2.1"
  lazy val jodaConvert = "org.joda" % "joda-convert" % "1.2"

  lazy val postgres = "postgresql" % "postgresql" % "9.2-1002.jdbc4"

  lazy val c3p0 = "c3p0" % "c3p0" % "0.9.1.2"

  lazy val slf4j = "org.slf4j" % "slf4j-api" % "1.7.2"
  lazy val log4jOverSlf4j = "org.slf4j" % "log4j-over-slf4j" % "1.7.2"

  lazy val logback = "ch.qos.logback" % "logback-classic" % "1.0.9"
  lazy val scalaIo = "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.1-seq"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "2.0.M5b"
}

//  ---------------------------------------------------------------------------

object ProjectDeps extends Dependencies {
  import Default._

  lazy val depsUtil = Deps(
    slf4j
  , scalaTest % "test"
  )

  lazy val depsIORC = Deps(
    scalaTest % "test"
  )

  lazy val depsBuilder = Deps(
    scalaIo
  )

  lazy val depsConvertersJava = Deps(
    jodaTime
  , jodaConvert
  , scalaTest % "test"
  )

  lazy val depsConvertersScala = Deps(
    pgscalaConvertersJava
  , scalaTest % "test"
  )

  lazy val depsPGScala = Deps(
    postgres
  , slf4j
  , pgscalaUtil
  , pgscalaIORC
  , pgscalaConvertersScala
  , scalaTest % "test"
  , logback % "test"
  )

  lazy val depsPool = Deps(
    c3p0
  , log4jOverSlf4j
  , pgscala
  , scalaTest % "test"
  , logback % "test"
  )
}

//  ###########################################################################

object PGScalaBuild extends Build {
  import BuildSettings._
  import ProjectDeps._

  lazy val util = Project(
    "util"
  , file("util")
  , settings = bsUtil :+ depsUtil
  )

  lazy val iorc = Project(
    "iorc"
  , file("iorc")
  , settings = bsIORC :+ depsIORC
  )

  lazy val builder = Project(
    "builder"
  , file("builder")
  , settings = bsBuilder :+ depsBuilder
  )

  lazy val pgjavaConverters = Project(
    "converters-java"
  , file("converters-java")
  , settings = bsConvertersJava :+ depsConvertersJava
  )

  lazy val pgscalaConverters = Project(
    "converters-scala"
  , file("converters-scala")
  , settings = bsConvertersScala :+ depsConvertersScala
  ) // dependsOn(pgjavaConverters)

  lazy val pgscala = Project(
    "pgscala"
  , file("pgscala")
  , settings = bsPGScala :+ depsPGScala
  ) // dependsOn(util, iorc, pgscalaConverters)

  lazy val pgscalaPool = Project(
    "pool"
  , file("pool")
  , settings = bsPool :+ depsPool
  ) // dependsOn(pgscala)
}

//  ---------------------------------------------------------------------------

object Repositories {
  val ElementNexus     = "Element Nexus"     at "http://repo.element.hr/nexus/content/groups/public/"
  val ElementReleases  = "Element Releases"  at "http://repo.element.hr/nexus/content/repositories/releases/"
  val ElementSnapshots = "Element Snapshots" at "http://repo.element.hr/nexus/content/repositories/snapshots/"
}

//  ---------------------------------------------------------------------------

object Resolvers {
  import Repositories._

  lazy val settings = Seq(
    resolvers := Seq(ElementNexus, ElementReleases, ElementSnapshots)
  , externalResolvers <<= resolvers map { r =>
      Resolver.withDefaultResolvers(r, mavenCentral = false)
    }
  )
}

//  ---------------------------------------------------------------------------

object Publishing {
  import Repositories._

  lazy val settings = Seq(
    publishTo <<= (version) { version => Some(
      if (version.endsWith("SNAPSHOT")) ElementSnapshots else ElementReleases
    )}
  , credentials += Credentials(Path.userHome / ".config" / "pgscala" / "nexus.config")
  , publishArtifact in (Compile, packageDoc) := false
  )
}

//  ---------------------------------------------------------------------------

object Default {
  //Eclipse plugin
  import com.typesafe.sbteclipse.plugin.EclipsePlugin._

  //Dependency report plugin
  import com.micronautics.dependencyReport.DependencyReport._

  val scala2_10 = Seq(
    "-feature"
  , "-language:postfixOps"
  , "-language:implicitConversions"
  , "-language:existentials"
  )

  val scala2_9_1 = Seq(
    "-Yrepl-sync"
  )

  lazy val scalaSettings =
    Defaults.defaultSettings ++
    eclipseSettings ++
    dependencyReportSettings ++
    Resolvers.settings ++
    Publishing.settings ++ Seq(
      organization := "org.pgscala"

    , crossScalaVersions := Seq("2.9.0", "2.9.0-1", "2.9.1", "2.9.1-1", "2.9.2", "2.9.3-RC1")
    , scalaVersion := "2.9.2"

    , javaHome := sys.env.get("JDK16_HOME").map(file(_))
    , javacOptions := Seq(
        "-deprecation"
      , "-encoding", "UTF-8"
      , "-Xlint:unchecked"
      , "-source", "1.6"
      , "-target", "1.6"
      )

    , scalacOptions <<= scalaVersion map ( sV => Seq(
          "-unchecked"
        , "-deprecation"
        , "-optimise"
        , "-encoding", "UTF8"
        , "-Xmax-classfile-name", "72"
        ) ++ (sV match {
          case x if (x startsWith "2.10.")                => scala2_9_1 ++ scala2_10
          case x if (x startsWith "2.9.") && x >= "2.9.1" => scala2_9_1
          case _ => Nil
        } )
      )

    , unmanagedSourceDirectories in Compile <<= (scalaSource in Compile)(_ :: Nil)
    , unmanagedSourceDirectories in Test    <<= (scalaSource in Test   )(_ :: Nil)
    )

  lazy val javaSettings =
    scalaSettings ++ Seq(
      autoScalaLibrary := false
    , crossPaths := false
    , javacOptions := Seq("-deprecation", "-encoding", "UTF-8", "-source", "1.6", "-target", "1.6")
    , unmanagedSourceDirectories in Compile <<= (javaSource in Compile)(_ :: Nil)
    )

  def Deps(libs: ModuleID*) = libraryDependencies ++= libs
}
