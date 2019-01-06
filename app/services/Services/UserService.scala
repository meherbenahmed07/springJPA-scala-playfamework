package services.Services


import java.{lang, util}

import Repository.UserRepository
import com.google.inject.ImplementedBy
import controllers.UserController
import Entity.User
import javax.inject.{Inject, Singleton}
import org.joda.time.DateTime
import org.springframework.data.domain.{Example, Page, Pageable, Sort}
import org.springframework.transaction.annotation.Transactional
import services.write.RegistrationForm

import scala.concurrent.Future
class UserService  {
  @Inject
  var userRepository:UserRepository=_
  @Transactional
  def registration(registration: RegistrationForm): Future[User] = {
    var newAccount: User = new User()
    newAccount.firstname = registration.firstName
    newAccount.lastname = registration.lastName
    newAccount.setPasswordHashCode(registration.password)
    newAccount.gender = registration.gender
    registration.birthday match {
      case Some(birthday) => newAccount.setDateOfBirth(new DateTime(birthday))
      case None => {}
    }
    newAccount.email = registration.email
   newAccount = userRepository.save(newAccount)
    Future.successful((newAccount))
  }
}