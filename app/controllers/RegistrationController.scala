package controllers

import controllers.ConstructorTestClass.ConstructorTestCaseClass
import controllers.util.HttpHelper
import models.Response.{Farragut, FarragutResponse}
import play.api.mvc.{Action, Controller}
import play.api._

/**
 * Created by Karthika on 4/7/2015.
 */
object RegistrationController extends Controller {

  def syncpointdetails = Action {
    val far = new Farragut("123","456")
    val test  = new FarragutResponse("200" ,"success", far);
   // Ok(JacksonWrapper.serialize((test)))
    HttpHelper.Ok("success" , "123", "test")
  }
}
