package charlert.village.creature

import charlert.village.creature.leg.NoLeg
import charlert.village.world.Coordinate
import charlert.village.world.World

class Villager(
    override var id: Int,
    override val world: World,
    override val coordinate: Coordinate,
    private var name: String
) : Creature {
    override val leg = NoLeg()
    override fun hello() {
        println("$name(${coordinate.x}, ${coordinate.y}): Hello!")
    }
}