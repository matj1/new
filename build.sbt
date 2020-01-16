name := "new"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
	"com.fasterxml.jackson.core" % "jackson-core" % "2.1.1",
	"com.fasterxml.jackson.core" % "jackson-annotations" % "2.1.1",
	"com.fasterxml.jackson.core" % "jackson-databind" % "2.1.1",
	"com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % "2.1.1"
)