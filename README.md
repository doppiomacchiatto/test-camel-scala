Project Overview:
This project depicts how-to configure camel, camel-scala, gradle and active-mq.  Scala has caught my 
attention and I decided to learn it while implementing Apache Camel (http://camel.apache.org/).
 
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

NOTE:  This project was first created with the maven archetype - camel-activemq.  This archetype is 
a camel based active-mq, spring project.