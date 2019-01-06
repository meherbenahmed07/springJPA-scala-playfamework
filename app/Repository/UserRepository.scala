package Repository

import com.google.inject.ImplementedBy
import Entity.User
import javax.inject.Inject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import services.Services.UserService
@ImplementedBy(classOf[UserService])
@Repository
trait UserRepository extends JpaRepository[User, String]{
}
