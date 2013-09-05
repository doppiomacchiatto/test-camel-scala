package esis.camel.scala

import org.apache.camel.main.Main
import org.apache.camel.scala.dsl.builder.RouteBuilderSupport
import org.apache.camel.impl.DefaultCamelContext
import org.apache.log4j.Logger
import esis.camel.scala.routes.MyRouteBuilder


/**
 * Scala: The App.class feature is solid,
 * which provides the equivalence of a Java Main.
 * In scala "object" represents a Singleton.
 */
object MyRouteMain extends App {
  val logger = Logger.getLogger(this.getClass.getName)
  val camelContext = new DefaultCamelContext
  camelContext.addRoutes(new MyRouteBuilder)
  logger.info("~//starting MyRouteMain...")
  camelContext.start

  Thread.sleep(2000)

  logger.info("stopping...")
  camelContext.stop

}

