package bankgegevensinlezen

/**
  * Created by kdroo on 09-06-17.
  */
class Filtering(transacties: Seq[Transactie]) {

  def filterOpAccount(Account: String): Seq[Transactie] = {
    transacties filter(t => t.voorRekeningNummer == Account)
  }

  def grootsteTransactie(): Transactie = {
    def recursive(t: Seq[Transactie], highest: Transactie): Transactie = t match {
      case x +: xs if x.bedrag > highest.bedrag => recursive(xs, x)
      case _ +: xs => recursive(xs, highest)
      case _ => highest
    }
    recursive(transacties, transacties.head)
  }

  def transactieVan(rekeninghouder: String): Seq[Transactie] = {
    if (rekeninghouder == "") transacties filter(transactie => "^$".r.findFirstIn(transactie.vanRekeningHouder).isDefined)
    else transacties filter(transactie => s"$rekeninghouder*".r.findFirstIn(transactie.vanRekeningHouder).isDefined)
  }

  def filterOpAlles(filter: String): Seq[Transactie] = {
    if (filter == "") transacties filter(transactie => "^$".r.findFirstIn(transactie.toString).isDefined)
    else transacties filter(transactie => s"$filter*".r.findFirstIn(transactie.toString).isDefined)
  }

  def splitsOpBedrag(splitsBedrag: Double): (Seq[Transactie], Seq[Transactie]) = {
    (transacties filter(transactie => transactie.bedrag >= splitsBedrag), transacties filter(transactie => transactie.bedrag < splitsBedrag))
  }
}
