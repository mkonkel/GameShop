package repository.remote.api

import io.ktor.client.HttpClient
import repository.remote.api.games.GamesApi
import repository.remote.api.login.RealLoginApi
import repository.remote.api.users.RealUsersApi
import repository.remote.api.users.UsersApi

internal class RealApiFactory(
    private val client: HttpClient,
    private val baseUrl: String
) : ApiFactory {
    override fun gamesApi(): GamesApi {
        TODO("Not yet implemented")
    }

    override fun usersApi(): UsersApi = RealUsersApi(client, baseUrl)

    override fun loginApi() = RealLoginApi(client, baseUrl)
}