package presentation.app

import dev.michalkonkel.gameshop.domain.user.User
import dev.michalkonkel.gameshop.repository.users.UsersRepository
import repository.remote.login.LoginRepository

internal class RealAppPresentation(
    private val loginRepository: LoginRepository,
    private val usersRepository: UsersRepository,
) : AppPresentation {
    override suspend fun getUsers(): List<User> {
        loginRepository.login("admin", "pass")

        return usersRepository.getUsers()
    }
}
