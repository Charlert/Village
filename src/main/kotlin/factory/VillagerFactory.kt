package charlert.village.factory

import charlert.village.creature.Villager
import charlert.village.world.Coordinate
import charlert.village.world.World

class VillagerFactory(override val world: World) : Factory {
    override fun create(): Int {
        val v = Villager(world.getLastId() + 1, world, getRandomCoordinate(), getRandomName())
        world.add(v)
        return v.id
    }

    private fun getRandomName(): String {
        val randomNames = setOf(
            "Chen Rui", "Wu Qian", "Ye Wenjie", "Qu Yuan", "Shi Qiang",
            "Guo Degang", "Chang Kaishen", "Luo Ji"
        )
        return randomNames.random()
    }

    private fun getRandomCoordinate(): Coordinate {
        val x = (0..world.mapWidth).random()
        val y = (0..world.mapHeight).random()
        return Coordinate(x, y)
    }
}