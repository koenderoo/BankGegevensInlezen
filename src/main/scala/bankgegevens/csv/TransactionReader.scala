package bankgegevens.csv

import bankgegevens.repo.Transaction

import scala.io.Source

/**
  * Created by kdroo on 16-06-17.
  */
class TransactionReader( fileName: String) {

  def readTransactions(): Seq[Transaction] = {
    for {
      line <- Source.fromFile(fileName).getLines().toVector
      values = line.split(",").map(_.trim)
    } yield Transaction(None, values(0), values(1), values(2), values(3), values(10).toDouble)

  }

}
