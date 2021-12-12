package charlert.village.event

import charlert.village.factory.VillagerFactory
import charlert.village.world.World

class EventController {
    private val world = World(32, 32)
    val villagerFactory = VillagerFactory(world)

    fun allRun(cmd: String) {
        for (c in world.getAll()) {
            run(cmd, c.id)
        }
    }

    fun run(cmd: String, id: Int) {
        when (cmd) {
            "hello" -> world.findById(id)?.hello()
            "die" -> world.findById(id)?.die()
            else -> println("error: undefined cmd \"$cmd\"")
        }
    }
}