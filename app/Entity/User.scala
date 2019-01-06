package Entity

import java.security.{MessageDigest, NoSuchAlgorithmException}

import javax.persistence._
import org.hibernate.annotations.{GenericGenerator, Type}
import org.joda.time.DateTime
import play.api.Logger
import scala.beans.BeanProperty

@Entity
class User extends Base {
  @Column(unique = false, length = 255)
  @BeanProperty
  var username: String = _

  var passwordHashCode: String = _


  var gender: String = _

  @BeanProperty
  var firstname: String = _

  @Column(length = 255)
  @BeanProperty
  var lastname: String = _

  @Type(`type` = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
  private var dateOfBirth: DateTime = _

  var address: String = _

  var email : String = _

  def setDateOfBirth(date: DateTime) = dateOfBirth = date

  def crypt(password: String): String = {
    var dd:String=password
    var m: MessageDigest = null
    try {
      m = MessageDigest.getInstance("MD5");
      m.update(dd.getBytes());
      val bytes = m.digest
      val sb = new StringBuilder
      for (i <- bytes) {
        sb.append(String.format("%02x", Byte.box(i)))
      }
     sb.toString()
      dd=sb.toString()
      return dd
    } catch {
      case e: NoSuchAlgorithmException => {
        Logger.error(e.toString)
        return null
      }
    }
  }

  def setPasswordHashCode(password: String) = {
    this.passwordHashCode = crypt(password)
  }
  def getdateOfBirth(): Option[scala.Long] = Option(dateOfBirth) match {
    case Some(value) => Some(value.getMillis)
    case None => None
  }
}
