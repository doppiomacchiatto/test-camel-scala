package esis.camel.test.routes


import org.apache.camel.test.junit4.CamelTestSupport
import org.junit.Test
import org.apache.camel.scala.dsl.builder.RouteBuilderSupport
import org.apache.camel.scala.dsl.builder.RouteBuilder

class XPathTestRouter extends  CamelTestSupport with RouteBuilderSupport{
	val endpoint="direct:a"
	val header = "HEADER"
	val headerValue = "CAMEL_TEST"
	/* Override the RouteBuilder creatreRouteBuilderMethod */
	override def createRouteBuilder() = this.createMyRouteBuilder
	@Test
	def testXPathContentBasedRouterLondon() {
	  val body = "<person><city>London</city></person>"
	  getMockEndpoint("mock:London").expectedBodiesReceived(body)	  
//	  {_.expectedBodiesReceived("<person><city>London</city></person)")}
	  template.sendBodyAndHeader(endpoint, body, header, headerValue)
	  assertMockEndpointsSatisfied()

  }
	@Test
	def testXPathContentBasedRouterOthers() {
	  val body = "<person><city>Miami</city></person>"
	  getMockEndpoint("mock:others").expectedBodiesReceived(body)	  
//	  {_.expectedBodiesReceived("<person><city>London</city></person)")}
	  template.sendBodyAndHeader(endpoint, body, header, headerValue)
	  assertMockEndpointsSatisfied()

  }
	def createMyRouteBuilder = new RouteBuilder {
     //START SNIPPET: cbr
	 "direct:a" ==> {
      choice {
        when(xpath("/person/city='London'")) { to("mock:London") }
        otherwise to ("mock:others")
      }
     }
	}

}