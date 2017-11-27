package bankgegevensinlezen

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.coding.Deflate
import akka.http.scaladsl.marshalling.ToResponseMarshaller
import akka.http.scaladsl.model.HttpMethods.GET
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{HttpApp, Route}
import akka.stream.ActorMaterializer
import bankgegevensinlezen.routes.{BaseRoutes, SimpleRoutes}

import scala.io.StdIn

/**
  * Created by kdroo on 13-06-17.
  */
object WebServer extends HttpApp {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  // needed for the future map/flatmap in the end
  implicit val executionContext = system.dispatcher

  // marshalling
  implicit val transactieM: ToResponseMarshaller[Transactie] = ???
  implicit val transactieSeqM: ToResponseMarshaller[Seq[Transactie]] = ???

  // backend entry points
  def retrieveTransacties: Seq[Transactie] = new BankgegevensCSVInlezer(getClass.getResource("/tr-info_13465084_20170609131129.CSV").getPath).leesTransacties()

//  lazy val  baseRoutes: HttpRequest => HttpResponse = {
//    case HttpRequest(GET, Uri.Path("/"), _, _, _) =>
//      HttpResponse(entity = HttpEntity(
//        ContentTypes.`text/html(UTF-8)`,
//        "<html><body>Hello world!</body></html>"
//      ))
//    case HttpRequest(GET, Uri.Path("/hello"), _, _, _) =>
//      HttpResponse(entity = HttpEntity(
//        ContentTypes.`text/html(UTF-8)`,
//        s"<html><body>Hello Amersfoort!</body></html>"
//      ))
//    case HttpRequest(GET, Uri.Path("/transacties"), _,_,_) =>
//      HttpResponse(entity = HttpEntity(
//        ContentTypes.`application/json`, retrieveTransacties))
//    case r: HttpRequest =>
//      r.discardEntityBytes() //import to drain incoming HTTP Entity stream
//      HttpResponse(404, entity = "Don't know where that comes from or has to go to")
//
//  }

  override def routes: Route = {
    path("/transacties") {

      get {
        encodeResponseWith(Deflate) {
          complete {
            // marshal custom object with in-scope marhsaller
            retrieveTransacties
          }
        }
      }

    }
  }

  // Starting the server
    WebServer.startServer("localhost", 8080)
}
