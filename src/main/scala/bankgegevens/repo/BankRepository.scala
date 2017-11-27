package bankgegevens.repo

import bankgegevens.connection._

import scala.concurrent.Future
/**
  * Created by kdroo on 15-06-17.
  */
trait BankRepository extends BankTable { this: DBComponent =>

  import driver.api._

  def createBank(bank: Bank): Future[Int] = db.run { bankTableAutoInc += bank }

  def updateBank(bank: Bank): Future[Int] = db.run { bankTableQuery.filter(_.id === bank.id.get).update(bank) }

  def getBankById(id: Int): Future[Option[Bank]] = db.run { bankTableQuery.filter(_.id === id).result.headOption }

  def getAllBanks(): Future[List[Bank]] = db.run { bankTableQuery.to[List].result }

  def deleteBank(id: Int): Future[Int] = db.run { bankTableQuery.filter(_.id ===id).delete }

  def bankDdl = db.run { bankTableQuery.schema.create }
}

trait BankTable { this: DBComponent =>

  import driver.api._

  class BankTable(tag: Tag) extends Table[Bank](tag, "bank") {
    val id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    val name = column[String]("name")
    def * = (name, id.?) <> (Bank.tupled, Bank.unapply)
  }

  protected val bankTableQuery = TableQuery[BankTable]

  protected def bankTableAutoInc = bankTableQuery returning bankTableQuery.map(_.id)

}

trait BankRepositoryImpl extends BankRepository with H2DBImpl

case class Bank(name: String, id: Option[Int] = None)
