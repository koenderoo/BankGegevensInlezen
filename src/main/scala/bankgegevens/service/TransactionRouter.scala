package bankgegevens.service

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.Directives._
import bankgegevens.json.JsonHelper
import bankgegevens.repo.TransactionRepository
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by kdroo on 15-06-17.
  */
trait TransactionRouter extends JsonHelper {
  this: TransactionRepository =>

  val transactionRoutes = {
    path("transaction" / IntNumber) { id =>
      get {
        complete {
          getTransactionById(id).map { result =>
            if (result.isDefined)
              HttpResponse(entity = write(result))
            else
              HttpResponse(entity = "This tranaction does not exist")
          }
        }
      }
    } ~
      path("transaction" / "all") {
        get {
          complete {
            getAllTransactions().map { result => HttpResponse(entity = write(result)) }
          }
        }
      } ~
    path("transaction" / "highest") {
      get {
        complete {
          getHighestTransaction().map { result => HttpResponse(entity = write(result)) }
        }
      }
    }
  }
}

