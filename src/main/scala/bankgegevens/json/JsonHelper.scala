package bankgegevens.json

import org.json4s.DefaultFormats
import org.json4s.JsonAST.{JNothing, JValue}
import org.json4s.native.{JsonParser, Serialization}

/**
  * Created by kdroo on 15-06-17.
  */
trait JsonHelper {

  implicit protected val formats = DefaultFormats

  protected def write[T <: AnyRef](value: T): String = Serialization.write(value)

  protected def parse(value: String): JValue = JsonParser.parse(value)

  implicit protected def extractOrEmptyString(json: JValue): String =  json match {
    case JNothing => ""
    case data     => data.extract[String]
  }


}
