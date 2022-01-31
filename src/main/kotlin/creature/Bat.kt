package charlert.village.creature

import charlert.village.creature.event.Event
import charlert.village.creature.leg.Wing
import charlert.village.god.Announcer
import charlert.village.god.Staff
import charlert.village.world.Location

class Bat(
    override val id: Int, override val staff: Staff, override val location: Location
) : Creature {
    override val leg = Wing()
    override val announcer = Announcer("Bat$id")
    override var name: String = "Bat$id"
    override val events = emptySet<Event>().toMutableSet()
    override fun hello() {
        announcer.send("A bat(${location.x}, ${location.y}): Z")
    }
}