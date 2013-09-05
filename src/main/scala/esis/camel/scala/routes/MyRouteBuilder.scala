package esis.camel.scala.routes

import org.apache.camel.Exchange
import java.util.logging.Logger
import org.apache.camel.scala.dsl.builder.RouteBuilder
import org.apache.camel.LoggingLevel
import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.camel.CamelContext
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.component.jms.JmsComponent
import java.util.logging.Level

/**
 * A Camel Router using the Scala DSL
 */
class MyRouteBuilder extends RouteBuilder {
  val logger = Logger.getLogger(this.getClass().getName())
  val tracing = false
  val context: CamelContext = new DefaultCamelContext
  val activeMqUrl: String = "vm://localhost"
  val connectionFactory = new ActiveMQConnectionFactory(activeMqUrl)
  context.addComponent("activemq", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory))
  logger.info("Component List: " + context.getComponentNames().toString())
  // an example of a Processor method
  val myProcessorMethod = (exchange: Exchange) => {
    exchange.getIn.setBody("block test")
  }
  val routeBuilder = new RouteBuilder {
    getContext.setTracing(tracing)
    "file:src/data?noop=true" ==> {
      errorHandler(loggingErrorHandler.level(LoggingLevel.INFO).logName("esis.camel.scala.MyRouteBuilder"))
      process(exchange => println("XML type order received: " + exchange.getIn().getHeader("CamelFileName")))
      to("activemq:test.queue")

    }
    "activemq:test.queue" ==> {
      process(exchange => println("XML type order received: " + exchange.getIn().getHeader("CamelFileName")))
      errorHandler(loggingErrorHandler.level(LoggingLevel.INFO).logName("esis.camel.scala.MyRouteBuilder"))
      choice {
        when(xpath("//city='London'")) { to("file:target/messages/uk") }
        otherwise to("file:target/messages/others")
      }
    }
  }
  context.addRoutes(routeBuilder)
  context.start
  while (true) {}

}
