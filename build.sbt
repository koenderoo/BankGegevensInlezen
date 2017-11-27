name := "BankGegevensInlezen"

version := "1.0"

scalaVersion := "2.12.1"

resolvers += Resolver.url("typesafe", url("http://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns)
//resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/ivy-releases/",
//  Resolver.bintrayRepo("hseeberger", "maven"))


libraryDependencies ++= {
  val AkkaHttpVersion = "10.0.7"
  val ScalaTestVersion = "3.0.1"
  val JunitTestVersion = "4.10"
  val Json4sVersion = "3.5.2"
  val SlickVersion = "3.2.0"
  Seq(
    "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
    "org.scalatest" %% "scalatest" % ScalaTestVersion % "test",
    "junit" % "junit" % JunitTestVersion % "test",
    "org.json4s"        %% "json4s-native"   % Json4sVersion,
    "org.json4s"        %% "json4s-ext"      % Json4sVersion,
    "com.typesafe.slick" %% "slick-hikaricp" % SlickVersion,
    "com.typesafe.slick" %% "slick" % SlickVersion,
    "ch.qos.logback" % "logback-classic" % "1.1.3",
    "com.h2database" % "h2" % "1.4.187"
  )
}