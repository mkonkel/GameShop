package repository.remote

import dev.michalkonkel.gameshop.repository.games.GamesRepository
import dev.michalkonkel.gameshop.repository.users.UsersRepository
import io.ktor.client.HttpClient
import repository.local.TokenStorage
import repository.remote.games.HttpGamesRepository
import repository.remote.login.HttpLoginRepository
import repository.remote.login.LoginRepository
import repository.remote.users.HttpUsersRepository

internal class RealRemoteRepositoryFactory(
    private val client: HttpClient,
    private val tokenStorage: TokenStorage,
) : RemoteRepositoryFactory {
    override fun loginRepository(): LoginRepository = HttpLoginRepository(client, tokenStorage)

    override fun gamesRepository(): GamesRepository = HttpGamesRepository(client)

    override fun usersRepository(): UsersRepository = HttpUsersRepository(client)
}
