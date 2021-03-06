name := "scala-ssh"

version := "0.6.0"

organization := "com.decodified"

organizationHomepage := Some(new URL("http://decodified.com"))

description := "A Scala library providing remote shell access via SSH"

homepage := Some(new URL("https://github.com/sirthias/scala-ssh"))

startYear := Some(2011)

licenses := Seq("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))

scalaVersion := "2.9.2"

scalacOptions := Seq("-deprecation", "-encoding", "utf8")

libraryDependencies ++= Seq(
	"net.schmizz" % "sshj" % "0.8.0",
	"org.slf4j" % "slf4j-api" % "1.6.4",
	"org.bouncycastle" % "bcprov-jdk16" % "1.46" % "provided",
	"com.jcraft" % "jzlib" % "1.1.1" % "provided",
	"org.specs2" %% "specs2" % "1.10" % "test",
	"ch.qos.logback" % "logback-classic" % "1.0.3" % "test"
)


///////////////
// publishing
///////////////

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

publishMavenStyle := true

crossPaths := false

publishTo <<= version { version =>
  Some {
    "spray nexus" at {
      // public uri is repo.spray.cc, we use an SSH tunnel to the nexus here
      "http://localhost:42424/content/repositories/" + {
        if (version.trim.endsWith("SNAPSHOT")) "snapshots/" else"releases/"
      }
    }
  }
}


///////////////
// ls-sbt
///////////////

seq(lsSettings:_*)

(LsKeys.tags in LsKeys.lsync) := Seq("ssh")

(LsKeys.docsUrl in LsKeys.lsync) := Some(new URL("https://github.com/sirthias/scala-ssh/"))

(externalResolvers in LsKeys.lsync) := Seq("spray repo" at "http://repo.spray.cc")