package charlert.village.creature

import charlert.village.creature.event.Event
import charlert.village.creature.leg.Leg
import charlert.village.god.Announcer
import charlert.village.god.Staff
import charlert.village.world.Location

interface Creature {
    var name: String
    val id: Int
    val staff: Staff
    val location: Location
    val leg: Leg
    val events: MutableSet<Event>
    val announcer: Announcer

    fun hello()

    fun getDistance(c: Creature): Float {
        return location.getDistance(c.location)
    }

    fun info(): String = "id: $id\n" + "coordinate: (${location.x}, ${location.y})\n" + "leg: $leg\n"

    fun die(reason: String) {
        announcer.send("I am dead. ($reason)")
        staff.remove(this)
    }

    private fun walk(x: Int, y: Int) {
        if (0 <= location.x + x && location.x + x <= staff.world.mapWidth - 1) location.x += x
        else if (x < 0) walk(x + 1, y) else walk(x - 1, y)
        if (0 <= location.y + y && location.y + y <= staff.world.mapHeight - 1) location.y += y
        else if (y < 0) walk(x, y + 1) else walk(x, y - 1)
    }

    fun walkRight() {
        walk(leg.speed, 0)
    }

    fun walkLeft() {
        walk(-leg.speed, 0)
    }

    fun walkNorth() {
        walk(0, leg.speed)
    }

    fun walkSouth() {
        walk(0, -leg.speed)
    }

    fun walkTo(target: Location) {
        if (target.x > location.x) walkRight()
        else if (target.x < location.x) walkLeft()
        if (target.y > location.y) walkNorth()
        else if (target.y < location.y) walkSouth()
    }

    fun kill(target: Creature) {
        if (isReachable(target)) {
            walkTo(target.location)
            target.die("killed by $name")
            announcer.send("I killed ${target.name}!")
        } else {
            announcer.send("I cannot kill ${target.name}, I cannot reach.")
        }
    }

    fun isReachable(target: Creature): Boolean {
        return getDistance(target) <= 3
    }

    fun getChar() = id.toString().toCharArray()[0]
    fun walkTo(target: Creature) {
        walkTo(target.location)
    }
}