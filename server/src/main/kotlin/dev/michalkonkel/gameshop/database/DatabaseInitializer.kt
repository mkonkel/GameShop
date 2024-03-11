package dev.michalkonkel.gameshop.database

import dev.michalkonkel.gameshop.database.tables.GamesEntity
import dev.michalkonkel.gameshop.database.tables.RoleEntity
import dev.michalkonkel.gameshop.database.tables.UserEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal fun addDefaultUsers() {
    val adminRole = RoleEntity.new { name = "admin" }
    val userRole = RoleEntity.new { name = "user" }

    UserEntity.new {
        name = "Admin"
        username = "admin"
        password = "pass"
        dateCreated = Clock.System.now().toLocalDateTime(TimeZone.UTC).date.toString()
        role = adminRole
    }

    UserEntity.new {
        name = "User"
        username = "user"
        password = "pass"
        dateCreated = Clock.System.now().toLocalDateTime(TimeZone.UTC).date.toString()
        role = userRole
    }
}

internal fun addDefaultGames() {
    GamesEntity.new {
        name = "Doom"
        description =
            "Doom is a first-person shooter game developed and published by id Software. " +
            "Released on December 10, 1993, for DOS, it is the first installment in the Doom franchise. " +
            "The player assumes the role of a space marine, later unofficially referred to as Doomguy, " +
            "fighting through hordes of undead humans and invading demons. The game begins on the moons of " +
            "Mars and finishes in hell, with the player traversing each level to find its exit or defeat " +
            "its final boss. It is an early example of 3D graphics in video games, and has enemies and " +
            "objects as 2D images, a technique sometimes referred to as 2.5D graphics."
        imageUrl = "https://upload.wikimedia.org/wikipedia/en/5/57/Doom_cover_art.jpg"
        price = "10.00"
    }

    GamesEntity.new {
        name = "Quake"
        description =
            "Quake is a first-person shooter game developed by id Software and published by GT " +
            "Interactive. The first game in the Quake series,[10] it was originally released for MS-DOS, " +
            "Microsoft Windows and Linux in 1996, followed by Mac OS and Sega Saturn in 1997 and " +
            "Nintendo 64 in 1998. In the game, players must find their way through various maze-like, " +
            "medieval environments while battling monsters using an array of weaponry. Quake takes " +
            "inspiration from gothic fiction and the works of H. P. Lovecraft."
        imageUrl = "https://upload.wikimedia.org/wikipedia/en/4/4c/Quake1cover.jpg"
        price = "5.00"
    }

    GamesEntity.new {
        name = "Duke Nukem 3D"
        description =
            "Duke Nukem 3D is a first-person shooter video game developed by 3D Realms. It is a sequel " +
            "to the platform games Duke Nukem and Duke Nukem II, published by 3D Realms. Duke Nukem 3D " +
            "features the adventures of the titular Duke Nukem, voiced by Jon St. John, who fights " +
            "against an alien invasion on Earth. Along with Wolfenstein 3D, Doom, Doom II, and Marathon, " +
            "Duke Nukem 3D is considered to be one of the many titles responsible for popularizing " +
            "first-person shooters, and was released to major acclaim. Reviewers praised the interactivity " +
            "of the environments, gameplay, level design, and unique risqué humor, a mix of pop-culture" +
            " satire and lampooning of over-the-top Hollywood action heroes. However, it also incited " +
            "controversy due to its violence, erotic elements, and portrayal of women."
        imageUrl = "https://upload.wikimedia.org/wikipedia/en/6/61/Duke_Nukem_3D_Coverart.png"
        price = "15.00"
    }

    GamesEntity.new {
        name = "Wolfenstein 3D"
        description =
            "Wolfenstein 3D is a first-person shooter video game developed by id Software and " +
            "published by Apogee Software and FormGen. Originally released on May 5, 1992, for DOS, " +
            "it was inspired by the 1981 Muse Software video game Castle Wolfenstein, and is the third " +
            "installment in the Wolfenstein series. In Wolfenstein 3D, the player assumes the role of " +
            "Allied spy William \"B.J.\" Blazkowicz during World War II as he escapes from the Nazi " +
            "German prison Castle Wolfenstein and carries out a series of crucial missions against the " +
            "Nazis. The player traverses each of the game's levels to find an elevator to the next level or " +
            "kill a final boss, fighting Nazi soldiers, dogs, and other enemies with a knife and a variety " +
            "of guns."
        imageUrl = "https://upload.wikimedia.org/wikipedia/en/0/05/Wolfenstein-3d.jpg"
        price = "12.00"
    }
}
