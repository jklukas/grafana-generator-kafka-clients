import sbt._

object Dependencies {
  lazy val kafkaConnectRuntime = "org.apache.kafka" % "connect-runtime" % "1.0.0"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4"
}
