package bankgegevens.service

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.Directives._
import bankgegevens.json.JsonHelper
import bankgegevens.repo.{Bank, BankRepository}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by kdroo on 15-06-17.
  */
trait BankRouter extends JsonHelper {
  this: BankRepository =>

  val bankRoutes = {
    path("bank" / IntNumber) { id =>
      get {
        complete {
          getBankById(id).map { result =>
            if (result.isDefined)
              HttpResponse(entity = write(result))
            else
              HttpResponse(entity = "This bank does not exist")
          }
        }
      }
    } ~
      path("bank" / "all") {
        get {
          complete {
            getAllBanks().map { result => HttpResponse(entity = write(result)) }
          }
        }
      } ~
      path("bank" / "save") {
        post {
          entity(as[String]) { bankJson =>
            complete {
              val bank = parse(bankJson).extract[Bank]
              createBank(bank).map { result => HttpResponse(entity = "Bank has been saved successfully") }
            }
          }
        }
      } ~
      path("bank" / "update") {
        post {
          entity(as[String]) { bankJson =>
            complete {
              val bank = parse(bankJson).extract[Bank]
              updateBank(bank).map { result => HttpResponse(entity = "Bank has  been updated successfully") }
            }
          }
        }
      } ~
      path("bank" / "delete" / IntNumber) { id =>
        post {
          complete {
            deleteBank(id).map { result => HttpResponse(entity = "Bank has been deleted successfully") }

          }
        }
      }
  }

}
