package bankgegevensinlezen.routes

import akka.http.scaladsl.model._
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.PathDirectives.{path, pathEndOrSingleSlash}
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.stream.ActorMaterializer

/**
  * Created by kdroo on 13-06-17.
  */
object BaseRoutes {
  // This route is the one that listens to the top level '/'
  lazy val  baseRoutes: HttpRequest => HttpResponse = {
    case HttpRequest(GET, Uri.Path("/"), _, _, _) =>
      HttpResponse(entity = HttpEntity(
        ContentTypes.`text/html(UTF-8)`,
        "<html><body>Hello world!</body></html>"
      ))
    case HttpRequest(GET, Uri.Path("/hello"), _, _, _) =>
      HttpResponse(entity = HttpEntity(
        ContentTypes.`text/html(UTF-8)`,
        s"<html><body>Hello Amersfoort!</body></html>"
      ))
//    case r: HttpRequest =>
//      r.discardEntityBytes() //import to drain incoming HTTP Entity stream
//      HttpResponse(404, entity = "Don't know where that comes from or has to go to")

  }
}
