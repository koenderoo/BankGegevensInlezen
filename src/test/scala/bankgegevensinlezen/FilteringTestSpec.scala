package bankgegevensinlezen

import org.scalatest.FunSuite
//import bankgegevensinlezen._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
  * Created by kdroo on 12-06-17.
  */
@RunWith(classOf[JUnitRunner])
class FilteringTestSpec extends FunSuite {

  // testdata
  val transacties = new BankgegevensCSVInlezer(getClass.getResource("/tr-info_13465084_20170609131129.CSV").getPath).leesTransacties()
  val f = new Filtering(transacties)
  val testdata = Seq(Transactie("01-04-2017","NL55SNSB0911531157","","",-20.0))

  test("testGrootsteTransactie") {

    assert(f.grootsteTransactie() == Transactie("22-05-2017","NL55SNSB0911531157","NL10RABO0138230986","trivento b.v.",3417.16))
  }

  test("testFilterOpAccount") {
    assert(f.filterOpAccount("NL55SNSB0911531157").size == 101)
  }

  test("transactieVan") {
    //test with full name
    assert(f.transactieVan("trivento b.v.").size == 5)
    // test with part of name
    assert(f.transactieVan("trivento").size == 5)
    // test with unknown
    assert(f.transactieVan("unknown").isEmpty)
    // test with no name
    assert(f.transactieVan("").size  > 100)
  }

  test("filter op alles") {
    // adds a few extra because of transactions between the two accounts
    val filtered = f.filterOpAlles("0911531157")
    assert(filtered.size == 105)
  }

  test("splits transacties op bedrag") {
    // splits in positieve en negatieve bedragen
    assert(f.splitsOpBedrag(0.0)._1.size == 47)
    f.splitsOpBedrag(0.0)._2.foreach(t => println(t))
    assert(f.splitsOpBedrag(0.0)._2.size == 237)
  }

}
