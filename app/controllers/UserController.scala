package controllers

import javax.inject.Inject
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.Services.UserService
import services.write.{RegistrationForm, RegistrationWrite}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserController extends Controller{
  @Inject
  var userService:UserService=_
  @Inject
  var registrationWrite: RegistrationWrite = _

  protected implicit def implicitRegistrationWrite = registrationWrite.writes
  def registration = Action.async(parse.urlFormEncoded) {
    implicit request =>
      val form: Form[RegistrationForm] = Form(
        mapping(
          "firstname" -> nonEmptyText,
          "lastname" -> nonEmptyText,
          "email" -> email,
          "password" -> nonEmptyText,
          "confirm_password" -> nonEmptyText,
          "birthday" -> optional(longNumber),
          "gender" ->nonEmptyText
        )(RegistrationForm.apply)(RegistrationForm.unapply)
          verifying("badrequest_password_confirmation", data => data.password.equals(data.confirm_password))
      )
      form.bindFromRequest.fold(
        formWithErrors => Future.successful({BadRequest(Json.toJson("form_error"))}),
        registration_data => {
          userService.registration(registration_data).map {
            response => {
              Ok(Json.toJson(response))
            }
          }.recover {
            case exception: Throwable => throw exception
          }
        }
      )
  }
}
