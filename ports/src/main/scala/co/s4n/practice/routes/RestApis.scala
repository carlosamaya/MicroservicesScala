package co.s4n.practice.routes

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import co.s4n.practice.dtos.dto
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.actor.{ActorRef, ActorSystem}
import co.s4n.practice.application.BusinessLogic.{CreateElement, DataDef}
import co.s4n.practice.dtos.dto.DataChunk

import scala.concurrent.ExecutionContextExecutor
// Routes
class RestApis(system: ActorSystem) extends RestApisRoutes {
  implicit def executionContext: ExecutionContextExecutor = system.dispatcher
}
// We'll define all endPoints in this Trait
trait RestApisRoutes {
  // Endpoint: GET /elements/{id_element}
  protected val getElementApi: Route = {
    pathPrefix("elements") {
      get {
        parameters('id_element.as[String]) { (id_element) =>
          complete(
            HttpEntity(ContentTypes.`text/html(UTF-8)`,
                       s"<h1>Get Element with id : ${id_element}</h1>"))
        }
      }
    }
  }
  // Endpoint: GET /elements/list
  protected val getElementListApi: Route = {
    pathPrefix("elements") {
      get {
        parameters('list.as[Int].*) { (list) =>
          //list.toList
          complete(
            HttpEntity(ContentTypes.`text/html(UTF-8)`,
                       s"<h1>Getting List Element with id's : ${list.toList}</h1>"))
        }
      }
    }
  }
  // Endpoint: POST /elements/:elements/create
  protected val postCreateElementApi: Route = {
    pathPrefix("elements"/ Segment / "create") { event =>
      post {
        pathEndOrSingleSlash {
          entity(as[DataChunk]) { request ⇒
            onSuccess(CreateElement(request.name_element)) {
              complete...
            }
          }
        }
      }
    }
  }
  // Routes with all endPoints availables
  val routes: Route = getElementApi ~ getElementListApi ~ postCreateElementApi
}
