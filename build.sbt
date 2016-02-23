import scala.collection.JavaConverters._

name := "marc4scala"

version := "1.0.1"

//organization := "org.xstudio"

// set scala version
scalaVersion := "2.11.7"

sources in (Compile, doc) := Seq.empty
publishArtifact in (Compile, packageDoc) := false


libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
  "org.apache.logging.log4j" % "log4j-core" % "2.0-beta8" % "test, runtime",
  "org.scalactic" %% "scalactic" % "2.2.6",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)