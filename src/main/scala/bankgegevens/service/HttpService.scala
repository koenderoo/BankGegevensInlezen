package bankgegevens.service

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import bankgegevens.csv.TransactionReader
import bankgegevens.repo.{Bank, BankRepositoryImpl, Transaction, TransactionRepositoryImpl}

/**
  * Created by kdroo on 15-06-17.
  */
object HttpService extends App
  with TransactionRouter with TransactionRepositoryImpl
  with BankRouter with BankRepositoryImpl  {

  implicit val system:ActorSystem = ActorSystem()

  implicit val materializer = ActorMaterializer()

  implicit val dispatcher= system.dispatcher

  bankDdl.onComplete{
    _ =>
      createBank(Bank("SBI"))
      createBank(Bank("PNB"))
      createBank(Bank("RBS"))
      transactionDdl
      def retrieveTransacties: Seq[Transaction] = new TransactionReader(getClass.getResource("/tr-info_13465084_20170609131129.CSV").getPath).readTransactions
      retrieveTransacties.map{t => createTransaction(t)}
      Http().bindAndHandle(bankRoutes ~ transactionRoutes, "localhost", 9000)
  }

  /**
    *
    * Insert data when start
    *
    */

}
