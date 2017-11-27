package bankgegevensinlezen.routes

//import akka.http.scaladsl.marshallers.xml.ScalaXmlSupport.defaultNodeSeqMarshaller
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
//import akka.http.scaladsl.server.Directives.{complete, get, path}
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete

/**
  * Created by kdroo on 13-06-17.
  */
trait SimpleRoutes {
  // This `val` holds one route (of possibly many more that will be part of your Web App)
  lazy val simpleRoutes =
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello from Amersfoort</h1>"))
      }
    }
}
