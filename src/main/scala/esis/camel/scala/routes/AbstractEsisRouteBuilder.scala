package esis.camel.scala.routes

import org.apache.camel.scala.dsl.builder.RouteBuilder

abstract class AbstractEsisRouteBuilder extends RouteBuilder {

  def defineMe(): String{}
}