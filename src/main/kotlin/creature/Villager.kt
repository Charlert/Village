package charlert.village.creature

import charlert.village.creature.event.Event
import charlert.village.creature.leg.NoLeg
import charlert.village.god.Announcer
import charlert.village.god.Staff
import charlert.village.world.Location

class Villager(
    override var id: Int,
    override val staff: Staff,
    override var location: Location,
    override var name: String
) : Creature {
    override val leg = NoLeg()
    override val announcer = Announcer(name)
    override val events: MutableSet<Event> = emptySet<Event>().toMutableSet()

    override fun hello() {
        println("$name(${location.x}, ${location.y}): Hello!")
    }

    override fun info(): String {
        return "name: $name\n" +
                super.info()
    }
}