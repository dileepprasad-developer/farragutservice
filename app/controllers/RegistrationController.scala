package controllers

import controllers.ConstructorTestClass.ConstructorTestCaseClass
import play.api.mvc.{Action, Controller}
import play.api._

/**
 * Created by Karthika on 4/7/2015.
 */
object RegistrationController extends Controller {

  def syncpointdetails = Action {

    val test =  JacksonWrapper
    val test1 = test.deserialize[ConstructorTestCaseClass]("""{"intValue":1,"stringValue":"foo"}""")
    println(test1.stringValue)
    val test2 = test.serialize(test1)
    Ok(test2)
  }
}
