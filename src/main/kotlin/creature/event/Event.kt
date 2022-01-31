package charlert.village.creature.event

import charlert.village.creature.Creature
import charlert.village.god.Staff

interface Event {
    val staff: Staff
    val owner: Creature
    fun execute()
}