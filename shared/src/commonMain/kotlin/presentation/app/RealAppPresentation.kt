package presentation.app

import dev.michalkonkel.gameshop.domain.roles.Role
import dev.michalkonkel.gameshop.domain.user.User
import dev.michalkonkel.gameshop.repository.users.UsersRepository
import kotlinx.coroutines.delay
import repository.remote.login.LoginRepository
import utils.stable.Stable

@Stable
internal class RealAppPresentation(
    private val loginRepository: LoginRepository,
    private val usersRepository: UsersRepository,
) : AppPresentation {
    override suspend fun getUsers(): List<User> {
//        loginRepository.login("admin", "pass")
//
//        return usersRepository.getUsers()

        delay(5000)
        return listOf(
            User("Michu", "Konkel", "mk", Role.ADMIN),
            User("Admin", "Admin", "admin", Role.ADMIN),
            User("User", "User", "user", Role.USER),
        )
    }
}
