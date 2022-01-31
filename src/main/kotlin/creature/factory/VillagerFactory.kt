package charlert.village.creature.factory

import charlert.village.creature.Creature
import charlert.village.creature.Villager
import charlert.village.god.Staff

class VillagerFactory(override val staff: Staff) : Factory {
    override fun create(id: Int): Creature {
        return Villager(id, staff, getRandomCoordinate(), getRandomName())
    }
}