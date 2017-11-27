import bankgegevensinlezen._

val inlezer = new BankgegevensCSVInlezer("/users/kdroo/Projecten/BankgegevensInlezen/src/main/resources/tr-info_13465084_20170609131129.CSV")

val transacties = new Filtering(inlezer)

//transacties.filterOpAccount("NL55SNSB0911531157").grootsteTransactie()
transacties.grootsteTransactie()
