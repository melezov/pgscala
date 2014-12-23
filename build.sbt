organization in ThisBuild         := "org.pgscala"

name                              := "pgscala"

version in ThisBuild              := "0.8.0-SNAPSHOT"

publishTo in ThisBuild            := Some(if (version.value endsWith "-SNAPSHOT") Opts.resolver.sonatypeSnapshots else Opts.resolver.sonatypeStaging)

licenses in ThisBuild             += ("BSD-style", url("http://opensource.org/licenses/BSD-3-Clause"))

startYear in ThisBuild            := Some(2011)

scmInfo in ThisBuild              := Some(ScmInfo(url("https://github.com/melezov/pgscala.git"), "scm:git:https://github.com/melezov/pgscala.git"))

pomExtra in ThisBuild             ~= (_ ++ {Developers.toXml})

publishMavenStyle in ThisBuild    := true

pomIncludeRepository in ThisBuild := { _ => false }

homepage in ThisBuild             := Some(url("https://pgscala.org/"))
