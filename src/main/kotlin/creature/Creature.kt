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

    fun getInformation(): String {
        return "id: $id\n" +
                "coordinate: (${coordinate.x}, ${coordinate.y})\n" +
                "leg: $leg\n"
    }

    fun die() {
        world.remove(this)
    }

    private fun walk(x: Int, y: Int) {
        when (0 <= coordinate.x + x && coordinate.x + x <= world.mapWidth - 1) {
            true -> coordinate.x += x
            else -> {
                if (x < 0) walk(x + 1, y) else walk(x - 1, y)
            }
        }
        when (0 <= coordinate.y + y && coordinate.y + y <= world.mapHeight - 1) {
            true -> coordinate.y += y
            else -> {
                if (y < 0) walk(x, y + 1) else walk(x, y - 1)
            }
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

    fun kill(target: Creature) {
        if (this.getDistance(target) <= 3) {
            target.die()
        }
    }
}