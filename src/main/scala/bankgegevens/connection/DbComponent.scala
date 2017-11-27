package bankgegevens.connection

/**
  * Created by kdroo on 15-06-17.
  */
import slick.jdbc.JdbcProfile

trait DBComponent {

  val driver: JdbcProfile

  import driver.api._


  val db: Database

}

