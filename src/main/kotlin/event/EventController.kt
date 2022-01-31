package charlert.village.event

import charlert.village.creature.action.VillagerAction
import charlert.village.creature.action.VillagerAction.*
import charlert.village.creature.action.WorldAction
import charlert.village.factory.VillagerFactory
import charlert.village.world.World

class EventController {
    private val world = World(32, 32)
    private val villagerFactory = VillagerFactory(world)

    fun run(action: WorldAction) {
        when (action) {
            WorldAction.create -> print(world.findById(villagerFactory.create())?.getInformation())
        }
    }

    fun run(action: VillagerAction, id: Int, targetId: Int = -1) {
        val villager = world.findById(id)
        val target = world.findById(targetId)
        if (villager == null) {
            println("error: $id not found")
            return
        }
        when (action) {
            hello -> villager.hello()
            die -> villager.die()
            show -> print(villager.getInformation())
            kill -> {
                if (target == null) {
                    println("error: $targetId not found")
                    return
                }
                villager.kill(target)
            }
        }
    }
}