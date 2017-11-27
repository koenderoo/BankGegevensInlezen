package bankgegevensinlezen

import scala.io.Source

/**
  * Created by kdroo on 09-06-17.
  */
class BankgegevensCSVInlezer(val fileName: String) extends BankgegevensInlezer {


  override def leesTransacties(): Seq[Transactie] = {
    for {
      line <- Source.fromFile(fileName).getLines().toVector
      values = line.split(",").map(_.trim)
    } yield Transactie(values(0), values(1), values(2), values(3), values(10).toDouble)
  }


}
