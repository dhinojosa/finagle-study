name := "finagle-study"

scalaVersion := "2.11.7"

version := "1.0"

fork in run := true

//Finagle httpx is Finagle's client server

libraryDependencies ++= Seq("com.twitter" %% "finagle-http" % "6.33.0",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.scala-lang.modules" % "scala-parser-combinators_2.11" % "1.0.4",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.4"
)

