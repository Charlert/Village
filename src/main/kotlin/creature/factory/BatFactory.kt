package charlert.village.creature.factory

import charlert.village.creature.Bat
import charlert.village.creature.Creature
import charlert.village.god.Staff

class BatFactory(override val staff: Staff) : Factory {
    override fun create(id: Int): Creature {
        return Bat(id, staff, getRandomCoordinate())
    }
}