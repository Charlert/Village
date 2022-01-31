package charlert.village.creature.factory

import charlert.village.creature.Creature
import charlert.village.god.Staff
import charlert.village.world.Location

interface Factory {
    val staff: Staff
    fun create(id: Int): Creature
    fun getRandomCoordinate(): Location {
        val x = (0 until staff.world.mapWidth).random()
        val y = (0 until staff.world.mapHeight).random()
        return Location(x, y)
    }

    fun getRandomName(): String {
        val randomNames = setOf(
            "Chen Rui", "Wu Qian", "Ye Wenjie", "Qu Yuan", "Shi Qiang", "Guo Degang", "Chang Kaishen", "Luo Ji"
        )
        return randomNames.random()
    }
}