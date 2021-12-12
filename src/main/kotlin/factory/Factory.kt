package charlert.village.factory

import charlert.village.world.World

interface Factory {
    val world: World
    fun create(): Int
}