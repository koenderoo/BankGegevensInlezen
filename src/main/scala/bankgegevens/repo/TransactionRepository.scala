package bankgegevens.repo

import bankgegevens.connection._


import scala.concurrent.Future
/**
  * Created by kdroo on 15-06-17.
  */
trait TransactionRepository extends TransactionTable { this: DBComponent =>

  import driver.api._

  def createTransaction(transaction: Transaction): Future[Int] = db.run { transactionTableAutoInc += transaction }

  def updateTransaction(transaction: Transaction): Future[Int] = db.run { transactionTableQuery.filter(_.id === transaction.id.get).update(transaction) }

  def getTransactionById(id: Int): Future[Option[Transaction]] = db.run { transactionTableQuery.filter(_.id === id).result.headOption }

  def getAllTransactions(): Future[List[Transaction]] = db.run { transactionTableQuery.to[List].result }

  def deleteTransaction(id: Int): Future[Int] = db.run { transactionTableQuery.filter(_.id === id).delete }

  def transactionDdl = db.run { transactionTableQuery.schema.create }

  def getHighestTransaction() = {
    db.run {
      transactionTableQuery.sortBy(_.bedrag.desc).take(1).result
    }
  }
}

trait TransactionTable { this: DBComponent =>

  import driver.api._

  class TransactionTable(tag: Tag) extends Table[Transaction](tag, "transaction") {
    val id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    val datum = column[String]("datum")
    val voorRekening = column[String]("voor_rekening")
    val vanRekening = column[String]("van_rekening")
    val vanRekeningHouder = column[String]("van_rekeninghouder")
    val bedrag = column[Double]("bedrag")
    def * = (id.?, datum, voorRekening, vanRekening, vanRekeningHouder, bedrag) <> (Transaction.tupled, Transaction.unapply _)
  }

  protected val transactionTableQuery = TableQuery[TransactionTable]

  protected def transactionTableAutoInc = transactionTableQuery returning transactionTableQuery.map(_.id)

}

trait TransactionRepositoryImpl extends TransactionRepository with H2DBImpl

case class Transaction(id: Option[Int] = None, datum: String, voorRekeningNummer: String, vanRekeningNummer: String, vanRekeningHouder: String, bedrag: Double) {
  override def toString = s"transactie: $bedrag voor rekeninnummer: $voorRekeningNummer en van rekeningnummer: $vanRekeningNummer van: $vanRekeningHouder op datum: $datum"
}