name := "farragutservice"

version := "1.0"

lazy val `farragutservice` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(jdbc, anorm, cache, ws)


libraryDependencies ++= List(
  "com.fasterxml.jackson.core"    % "jackson-core"          % "2.4.1",
  "com.fasterxml.jackson.core"    % "jackson-databind"      % "2.4.1",
  "com.fasterxml.jackson.module"  %% "jackson-module-scala" % "2.4.1"
)


unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")