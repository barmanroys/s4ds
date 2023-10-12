name := "Logistic_regression"

organization := "My Organisation"

version := "0.1.0-SNAPSHOT"

scalaVersion := "3.2.2" //  2.11.7 according to the book repository

libraryDependencies ++= Seq(
  "org.scalanlp" %% "breeze" % "2.1.0",
  "org.scalanlp" %% "breeze-natives" % "2.1.0",
  "org.slf4j" % "slf4j-simple" % "2.0.5"
)

assemblyMergeStrategy in assembly := {
  case "META-INF/versions/9/module-info.class" => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

