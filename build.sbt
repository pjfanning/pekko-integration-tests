ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.16"

val pekkoVersion = "1.2.0-M2"

lazy val root = (project in file("."))
  .settings(
    name := "pekko-integration-tests"
  )
  .aggregate(testCommon, remoteTests)

lazy val testCommon = (project in file("test-common"))
  .settings(
    name := "test-common",
    libraryDependencies ++= Seq(
      "org.apache.pekko" %% "pekko-actor" % pekkoVersion,
      "org.apache.pekko" %% "pekko-remote" % pekkoVersion,
      "org.apache.pekko" %% "pekko-testkit" % pekkoVersion,
      "org.scalatest" %% "scalatest" % "3.2.19"
    )
  )

lazy val remoteTests = (project in file("remote-tests"))
  .settings(
    name := "remote-tests",
    libraryDependencies ++= Seq(
      "org.apache.pekko" %% "pekko-actor" % pekkoVersion % Test,
      "org.apache.pekko" %% "pekko-remote" % pekkoVersion % Test,
      "org.apache.pekko" %% "pekko-protobuf-v3" % pekkoVersion % Test,
      "org.apache.pekko" %% "pekko-testkit" % pekkoVersion % Test,
      "org.apache.pekko" %% "pekko-multi-node-testkit" % pekkoVersion % Test
    )
  )
  .dependsOn(testCommon % Test)

