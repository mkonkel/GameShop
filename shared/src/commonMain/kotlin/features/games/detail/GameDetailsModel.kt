package features.games.detail

import features.utils.Model

data class GameDetailsModel(
    val name: String = "Quake 3",
    val description: String =
        "Quake III Arena is a multiplayer-focused first-person shooter video game released in " +
            "December 1999. The game was developed by id Software and featured music composed by Sonic Mayhem and " +
            "Front Line Assembly founder Bill Leeb. Quake III Arena is the third game in the Quake series and differs " +
            "from previous games by excluding a traditional single-player element and focusing on multi-player action. " +
            "The single-player mode is played against computer-controlled bots.",
    val image: String = "https://upload.wikimedia.org/wikipedia/en/9/9b/Quake_III_Arena_cover.png",
    val rating: Int = 5,
) : Model
