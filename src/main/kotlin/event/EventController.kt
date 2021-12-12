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
        val c = world.findById(id)
        if (c == null) {
            println("error: $id not found")
            return
        }
        when (cmd) {
            "hello" -> c.hello()
            "die" -> c.die()
            "show" -> print(c.getInformation())
            else -> println("error: undefined cmd \"$cmd\"")
        }
    }

    fun worldRun(cmd: String) {
        when (cmd) {
            "add a villager" -> print(world.findById(villagerFactory.create())?.getInformation())
            else -> println("error: undefined cmd \"$cmd\"")
        }
    }
}