package repository.remote.api

import repository.remote.api.games.GamesApi
import repository.remote.api.login.LoginApi
import repository.remote.api.users.UsersApi

interface ApiFactory {
    fun gamesApi(): GamesApi
    fun usersApi(): UsersApi
    fun loginApi(): LoginApi
}