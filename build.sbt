ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.16"

val pekkoVersion = "1.2.0-M2"
// Java 8 friendly versions that we link in our releases
val aeronVersion = "1.45.1"
val agronaVersion = "1.22.0"
val nettyVersion = "4.2.3.Final"
// latest aeron and agrona versions
// val aeronVersion = "1.48.5"
// val agronaVersion = "2.2.4"

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
      "org.apache.pekko" %% "pekko-multi-node-testkit" % pekkoVersion % Test,
      "io.aeron" % "aeron-driver" % aeronVersion % Test,
      "io.aeron" % "aeron-client" % aeronVersion % Test,
      "org.agrona" % "agrona" % agronaVersion % Test,
      "io.netty" % "netty-handler" % nettyVersion % Test,
      "io.netty" % "netty-transport" % nettyVersion % Test
    )
  )
  .dependsOn(testCommon % Test)

