package charlert.village.creature

import charlert.village.creature.leg.Wing
import charlert.village.world.Coordinate
import charlert.village.world.World

class Bat(override val id: Int, override val world: World, override val coordinate: Coordinate) : Creature {
    override val leg = Wing()
    override fun hello() {
        println("A bat(${coordinate.x}, ${coordinate.y}): Z")
    }
}