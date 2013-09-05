This project depicts how-to configure camel-scala with gradle and activmq.  Scala has caught my 
attention and I decided to learn it while invoking Apache Camel (http://camel.apache.org/).

This project builds on top of the maven archetype camel-activemq.  There are many advantages to moving
away from Maven and to use Gradle.  The first notable difference is the speed of your build.  Gradle
can leverage your M2 repository and Maven Central as well as Ivy.  It's really the "Swiss Army knife
of build + dependency management.

Scope:
The project has a camel route, MyRouteBuilder, which reads .xml files from the file system, send the xml
message to an activemq queue.  The logic then routes the message from the queue to an endpoint based
on an xpath expression ("/person/city='London'").

To run the project from the command line:

1. Make sure that you have Gradle installed
2. :> gradle clean runSimple
3  :> gradle test

To run the project from eclipse:
1. Install the Gradle Plugin
2. Install the Scala Plugin.
3. Open esis.camel.scala.MyRouteMain
4. Right Click --> Run As --> Scala application.