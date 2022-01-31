package charlert.village.creature.event

import charlert.village.creature.Creature
import charlert.village.god.Staff

class Chase(override val staff: Staff, override val owner: Creature, private val target: Creature) : Event {
    override fun execute() {
        println("${target.name} is chasing ${target.name}")
        if (owner.isReachable(target)) {
            owner.kill(target)
            staff.remove(this)
        } else {
            owner.walkTo(target)
        }
    }
}