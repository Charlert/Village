package charlert.village.creature.event

import charlert.village.creature.Creature
import charlert.village.god.Staff
import charlert.village.world.Location

class Escape(override val staff: Staff, override val owner: Creature, val from: Creature) : Event {
    override fun execute() {
        println("${owner.name}は${from.name}から逃げ出だしてます。")
        owner.walkTo(
            Location(
                owner.location.x - (from.location.x - owner.location.x),
                owner.location.y - (from.location.y - owner.location.y)
            )
        )
    }
}