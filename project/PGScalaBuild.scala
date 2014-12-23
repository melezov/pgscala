import sbt._
import Keys._

import com.typesafe.sbteclipse.plugin.EclipsePlugin._
import net.virtualvoid.sbt.graph.Plugin._

//  ---------------------------------------------------------------------------

trait Dependencies {
  val postgresJdbc = "org.postgresql" % "postgresql" % "9.3-1102-jdbc4"

  val jodaTime = "joda-time" % "joda-time" % "2.6"
  val jodaConvert = "org.joda" % "joda-convert" % "1.7"

  val slf4j = "org.slf4j" % "slf4j-api" % "1.7.7"
  val logback = "ch.qos.logback" % "logback-classic" % "1.1.2"

  val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.0.2"
  val scalaIo = "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.3-1"

  val junit = "junit" % "junit" % "4.12"
  val scalaTest = "org.scalatest" %% "scalatest" % "2.2.3"
}

//  ---------------------------------------------------------------------------

trait BuildSettings {

  lazy val scalaSettings =
    Defaults.defaultSettings ++
    eclipseSettings ++
    graphSettings ++ Seq(
      crossScalaVersions := Seq("2.11.4")
    , scalaVersion := crossScalaVersions.value.head
    , scalacOptions := Seq(
        "-deprecation"
      , "-encoding", "UTF-8"
      , "-feature"
      , "-language:existentials"
      , "-language:implicitConversions"
      , "-language:postfixOps"
      , "-language:reflectiveCalls"
      , "-optimise"
      , "-unchecked"
      , "-Xcheckinit"
      , "-Xlint"
      , "-Xmax-classfile-name", "72"
      , "-Xno-forwarders"
      , "-Xverify"
      , "-Yclosure-elim"
      , "-Yconst-opt"
      , "-Ydead-code"
      , "-Yinline-warnings"
      , "-Yinline"
      , "-Yrepl-sync"
      , "-Ywarn-adapted-args"
      , "-Ywarn-dead-code"
      , "-Ywarn-inaccessible"
      , "-Ywarn-infer-any"
      , "-Ywarn-nullary-override"
      , "-Ywarn-nullary-unit"
      , "-Ywarn-numeric-widen"
      , "-Ywarn-unused"
      )

    , javacOptions := Seq(
        "-encoding", "UTF-8"
      , "-deprecation"
      , "-Xlint"
      , "-target", "1.6"
      , "-source", "1.6"
      ) ++ (sys.env.get("JDK16_HOME") match {
        case Some(jdk16Home) => Seq("-bootclasspath", jdk16Home + "/jre/lib/rt.jar")
        case _ => Nil
      })

    , EclipseKeys.eclipseOutput := Some(".target")
    , EclipseKeys.executionEnvironment := Some(EclipseExecutionEnvironment.JavaSE16)
    )

  lazy val javaSettings =
    scalaSettings ++ Seq(
      autoScalaLibrary := false
    , crossPaths := false
    )

//  ---------------------------------------------------------------------------

  val proxy = TaskKey[Unit]("proxy", "Compiles jasmin proxy methods (return type overloading for Scala)")
  val proxyTask = proxy := {
    val src = baseDirectory.value / ".."  / "converters-scala" /
      "src" / "generated" / "jasmin" / "org" / "pgscala" / "converters" / "PGNullableConverter.j"

    for (target <- Seq("target", ".target")) {
      val dst = baseDirectory.value / ".."  / "converters-scala" /
        target / ("scala-" + scalaBinaryVersion.value) / "classes"

      jasmin.Main.main(Array(src.absolutePath, "-d", dst.absolutePath))
    }
  }
}

//  ---------------------------------------------------------------------------

object PGScalaBuild extends Build with BuildSettings with Dependencies {
  lazy val util = Project(
    "util"
  , file("util")
  , settings = javaSettings ++ Seq(
      name    := "pgscala-util"
    , version := "0.0.0-SNAPSHOT"
    , initialCommands := "import org.pgscala.util._"
    , libraryDependencies ++= Seq(
        slf4j
      , junit % "test"
      , scalaTest % "test"
      , postgresJdbc % "test"
      , logback % "test"
      )
    , unmanagedSourceDirectories in Compile := Seq((javaSource in Compile).value)
    , unmanagedSourceDirectories in Test := Seq((scalaSource in Test).value)
    , EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource
    )
  )

  lazy val builder = Project(
    "builder"
  , file("builder")
  , settings = scalaSettings ++ Seq(
      name    := "builder"
    , version := "0.0.0-SNAPSHOT"
    , proxyTask
    , libraryDependencies ++= Seq(
        scalaIo
      )
    , unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value)
    , unmanagedSourceDirectories in Test := Nil
    , EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource
    )
  )

  lazy val pgjavaConverters = Project(
    "converters-java"
  , file("converters-java")
  , settings = javaSettings ++ Seq(
      name    := "pgscala-converters-java"
    , version := "0.0.0-SNAPSHOT"
    , initialCommands := "import org.pgscala.converters._"
    , libraryDependencies ++= Seq(
        jodaTime
      , jodaConvert
      , junit % "test"
      , scalaTest % "test"
      )
    , unmanagedSourceDirectories in Compile := Seq(sourceDirectory.value / "generated" / "java")
    , unmanagedSourceDirectories in Test := Seq((scalaSource in Test).value)
    )
  )

  lazy val pgscalaConverters = Project(
    "converters-scala"
  , file("converters-scala")
  , settings = scalaSettings ++ Seq(
      name    := "pgscala-converters-scala"
    , version := "0.0.0-SNAPSHOT"
    , initialCommands := "import org.pgscala.converters._"
    , libraryDependencies ++= Seq(
        scalaXml
      , junit % "test"
      , scalaTest % "test"
      )
    , unmanagedSourceDirectories in Compile := Seq(
        sourceDirectory.value / "generated" / "java"
      , sourceDirectory.value / "generated" / "jasmin"
      , sourceDirectory.value / "generated" / "scala"
      , (scalaSource in Compile).value
      )
    , unmanagedSourceDirectories in Test := Seq((scalaSource in Test).value)
    )
  ) dependsOn(pgjavaConverters, util % "test->compile")

  lazy val pgscala = Project(
    "pgscala-commons"
  , file("pgscala")
  , settings = scalaSettings ++ Seq(
      name    := "pgscala-commons"
    , version := "0.0.0-SNAPSHOT"
    , initialCommands := "import org.pgscala._"
    , libraryDependencies ++= Seq(
        postgresJdbc
      , slf4j
      , junit % "test"
      , scalaTest % "test"
      , logback % "test"
      )
    , unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value)
    , unmanagedSourceDirectories in Test := Seq((scalaSource in Test).value)
    , EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource
    )
  ) dependsOn(util, pgscalaConverters)

  val projs: Seq[sbt.ProjectReference] = Seq(pgscalaConverters, pgjavaConverters, util, pgscala)

  def aggregatedCompile =  ScopeFilter(inProjects(projs: _*), inConfigurations(Compile))

  def aggregatedTest = ScopeFilter(inProjects(projs: _*), inConfigurations(Test))

  def aggregatedModules = Seq(
    sources in Compile                        := sources.all(aggregatedCompile).value.flatten,
    unmanagedSources in Compile               := unmanagedSources.all(aggregatedCompile).value.flatten,
    unmanagedSourceDirectories in Compile     := unmanagedSourceDirectories.all(aggregatedCompile).value.flatten,
    unmanagedResourceDirectories in Compile   := unmanagedResourceDirectories.all(aggregatedCompile).value.flatten,
    sources in Test                           := sources.all(aggregatedTest).value.flatten,
    unmanagedSources in Test                  := unmanagedSources.all(aggregatedTest).value.flatten,
    unmanagedSourceDirectories in Test        := unmanagedSourceDirectories.all(aggregatedTest).value.flatten,
    unmanagedResourceDirectories in Test      := unmanagedResourceDirectories.all(aggregatedTest).value.flatten,
    libraryDependencies                       := libraryDependencies.all(aggregatedCompile).value.flatten
  )

  lazy val root = (project in file(".")) settings ((scalaSettings ++ aggregatedModules): _*)
}
