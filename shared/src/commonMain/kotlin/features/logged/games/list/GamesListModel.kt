package features.logged.games.list

import features.utils.Model

data class GamesListModel(
    val games: List<String> =
        listOf(
            "Doom",
            "Doom 2",
            "Quake",
            "Quake 2",
            "Quake 3",
            "Duke Nukem 3D",
            "Duke Nukem Forever",
            "Duke Nukem 3D: 20th Anniversary World Tour",
            "Duke Nukem 3D: Megaton Edition",
            "Duke Nukem 3D: Atomic Edition",
            "Heretic",
            "Hexen",
            "Hexen 2",
            "Blood",
            "Blood 2",
        ),
) : Model
