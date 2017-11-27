package bankgegevens.connection

import org.slf4j.LoggerFactory

/**
  * Created by kdroo on 15-06-17.
  */
trait H2DBImpl extends DBComponent {

  val logger = LoggerFactory.getLogger(this.getClass)

  val driver = slick.jdbc.H2Profile

  import driver.api._

  val h2Url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"

  val db: Database = {
    logger.info("Creating test connection ..................................")
    Database.forURL(url = h2Url, driver = "org.h2.Driver")

  }
}
