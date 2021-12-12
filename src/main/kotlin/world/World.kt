package charlert.village.world

import charlert.village.creature.Creature

class World(val mapWidth: Int, val mapHeight: Int) {
    private val creatures = mutableSetOf<Creature>()
    fun add(c: Creature) {
        creatures.add(c)
    }

    fun remove(c: Creature) {
        creatures.remove(c)
    }

    fun findById(id: Int): Creature? {
        for (c in creatures)
            if (c.id == id)
                return c
        return null
    }

    fun getDistance(c1: Creature, c2: Creature): Float {
        return c1.getDistance(c2)
    }

    fun getAll(): List<Creature> {
        val ml = mutableListOf<Creature>()
        for (c in creatures) ml.add(c)
        return ml
    }

    fun getLastId(): Int {
        var lastId = 0
        for (c in creatures) if (c.id > lastId) lastId = c.id
        return lastId
    }
}