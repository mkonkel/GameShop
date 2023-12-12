package dev.michalkonkel.gameshop.di

import dev.michalkonkel.gameshop.database.DatabaseFactory
import dev.michalkonkel.gameshop.database.RealDatabaseFactory
import dev.michalkonkel.gameshop.features.games.data.GamesDAOFacade
import dev.michalkonkel.gameshop.features.games.data.RealGamesDAOFacade
import dev.michalkonkel.gameshop.features.games.domain.RealDatabaseGamesRepository
import dev.michalkonkel.gameshop.features.users.data.dao.RealUsersDAOFacade
import dev.michalkonkel.gameshop.features.users.data.dao.UsersDAOFacade
import dev.michalkonkel.gameshop.features.users.domain.DatabaseUsersRepository
import dev.michalkonkel.gameshop.features.users.domain.RealDatabaseUsersRepository
import dev.michalkonkel.gameshop.repository.GamesRepository
import org.koin.dsl.module

val appModule = module {
    single<DatabaseFactory> { RealDatabaseFactory() }

    single<UsersDAOFacade> { RealUsersDAOFacade() }
    single<GamesDAOFacade> { RealGamesDAOFacade() }

    single<DatabaseUsersRepository> { RealDatabaseUsersRepository(get()) }
    single<GamesRepository> { RealDatabaseGamesRepository(get()) }
}