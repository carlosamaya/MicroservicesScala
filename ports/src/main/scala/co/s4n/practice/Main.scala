package co.s4n.practice

import co.s4n.practice.application.env.Logger
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import co.s4n.practice.routes.RestApis
import akka.http.scaladsl.Http
import scala.concurrent._
import akka.http.scaladsl.Http.ServerBinding

object Main extends App with Logger {
  loggerInformation("Practice Microservice has Started")
  implicit val system = ActorSystem("s4nPractice")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  val portInUse = 8085
  // Define endpoints routes Object
  val endPointsObject = new RestApis(system).routes
  //Server Binding in localhost, port=portInUse
  // We can optimize this using config files
  val bindingFuture: Future[ServerBinding] =
    Http().bindAndHandle(endPointsObject,
                         interface = "localhost",
                         port = portInUse)
  // We start service and log the info
  discard {
    try {
      bindingFuture.map { serverBinding ⇒
        loggerInformation(
          s"RestApi bound to http://localhost:${portInUse}/CUSTOM_ROUTE ${serverBinding.localAddress}")
      }
    } catch {
      //    If the HTTP server fails to start, we throw an Exception and log the error
      //    and close the system
      case ex: Exception ⇒
        loggerInformation(
          s"Failed to bind to http://localhost:${portInUse}/CUSTOM_ROUTE ${ex.getMessage}")
        //      System shutdown
        system.terminate()
    }
  }
  // Routine to discard Unit return validation
  def discard(evaluateForSideEffectOnly: Any): Unit = {
    val _: Any = evaluateForSideEffectOnly
    () //Return unit to prevent warning due to discarding value
  }
}
