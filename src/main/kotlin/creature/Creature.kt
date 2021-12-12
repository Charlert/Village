package charlert.village.creature

import charlert.village.creature.leg.Leg
import charlert.village.world.Coordinate
import charlert.village.world.World

interface Creature {
    val id: Int
    val world: World
    val coordinate: Coordinate
    val leg: Leg

    fun hello()

    fun getDistance(c: Creature): Float {
        return coordinate.getDistance(c.coordinate)
    }

    fun die() {
        world.remove(this)
    }

    private fun walk(x: Int, y: Int) {
        when (0 <= coordinate.x + x && coordinate.x + x <= world.mapWidth - 1) {
            true -> coordinate.x += x
            else -> {}
        }
        when (0 <= coordinate.y + y && coordinate.y + y <= world.mapHeight - 1) {
            true -> coordinate.y += y
            else -> {}
        }
    }

    fun walkRight() {
        walk(-leg.speed, 0)
    }

    fun walkLeft() {
        walk(leg.speed, 0)
    }

    fun walkNorth() {
        walk(0, leg.speed)
    }

    fun walkSouth() {
        walk(0, -leg.speed)
    }
}