package bankgegevensinlezen

/**
  * Created by kdroo on 09-06-17.
  */
trait BankgegevensInlezer {

  /**
    * @return a [[Seq]] containing all transactions
    */
  def leesTransacties(): Seq[Transactie]
}

case class Transactie(datum: String, voorRekeningNummer: String, vanRekeningNummer: String, vanRekeningHouder: String, bedrag: Double) {
  override def toString = s"transactie: $bedrag voor rekeninnummer: $voorRekeningNummer en van rekeningnummer: $vanRekeningNummer van: $vanRekeningHouder op datum: $datum"
}

