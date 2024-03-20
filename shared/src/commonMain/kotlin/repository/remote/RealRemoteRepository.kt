package repository.remote

import dev.michalkonkel.gameshop.repository.games.GamesRepository
import dev.michalkonkel.gameshop.repository.users.UsersRepository
import repository.local.token.TokenStorage
import repository.remote.client.HttpClientFactory
import repository.remote.games.HttpGamesRepository
import repository.remote.login.HttpLoginRepository
import repository.remote.login.LoginRepository
import repository.remote.users.HttpUsersRepository

internal class RealRemoteRepository(
    clientFactory: HttpClientFactory,
    private val tokenStorage: TokenStorage,
) : RemoteRepository {
    private val client = clientFactory.create()

    override fun loginRepository(): LoginRepository = HttpLoginRepository(client, tokenStorage)

    override fun gamesRepository(): GamesRepository = HttpGamesRepository(client)

    override fun usersRepository(): UsersRepository = HttpUsersRepository(client)
}
