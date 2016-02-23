import scala.collection.JavaConverters._

name := "marc4scala"

version := "1.0.1"

//organization := "org.xstudio"

// set scala version
scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "org.scalactic" %% "scalactic" % "2.2.6",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)
