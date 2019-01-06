package services.write

import Entity.User
import play.api.libs.json.{Json, Writes}

class RegistrationWrite {
  val writes: Writes[User] = Writes {
    (color: User) => {
      var response = Json.obj(
        "id" -> color.id,
        "firstname" -> color.firstname,
        "lastname" -> color.lastname,
        "email"->color.email,
        "gender"->color.gender,
        "birthday"->Json.toJson(color.getdateOfBirth())
      )
      response
    }
  }
}
