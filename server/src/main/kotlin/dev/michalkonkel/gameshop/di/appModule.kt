package dev.michalkonkel.gameshop.di

import dev.michalkonkel.gameshop.features.games.domain.DatabaseGamesRepository
import dev.michalkonkel.gameshop.repository.GamesRepository
import org.koin.dsl.module

val appModule = module {
    // TODO add database
    single<GamesRepository> { DatabaseGamesRepository() }
}