name := """unq-ui-planificador"""

version := "1.0"

scalaVersion := "2.10.2"

resolvers += "Local Maven Repository" at Path.userHome.asFile.toURI.toURL +"/.m2/repository"

resolvers += "Uqbar repo releases" at "http://uqbar-wiki.org/mvn/releases"

resolvers += "Uqbar repo snapshots" at "http://uqbar-wiki.org/mvn/snapshots"

resolvers += "SWT repo" at "http://swt-maven.googlecode.com/git-history/master/"

resolvers += "swt-repo" at "https://swt-repo.googlecode.com/svn/repo/"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

libraryDependencies += "org.uqbar-project" % "arena-jface" % "3.3-SNAPSHOT"

libraryDependencies += "joda-time" % "joda-time" % "2.3"

libraryDependencies += "com.github.nscala-time" % "nscala-time_2.10" % "0.2.0"

libraryDependencies += {
  val os = (sys.props("os.name"), sys.props("os.arch")) match {
    case ("Linux", _) => "gtk.linux.x86_64"
    case ("Mac OS X", "amd64" | "x86_64") => "cocoa.macosx.x86_64"
    case ("Mac OS X", _) => "cocoa.macosx.x86"
    case (os, "amd64") if os.startsWith("Windows") => "win32.win32.x86_64"
    case (os, _) if os.startsWith("Windows") => "win32.win32.x86"
    case (os, arch) => sys.error("Cannot obtain lib for OS '" + os + "' and architecture '" + arch + "'")
  }
  val artifact = "org.eclipse.swt." + os
  "org.eclipse.swt" % artifact % "3.8"
}

transitiveClassifiers in Global := Seq(Artifact.SourceClassifier)