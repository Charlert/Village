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

    fun getCreature(id: Int): Creature? = creatures.find { it.id == id }

    private fun getCreature(x: Int, y: Int): Creature? =
        creatures.firstOrNull { it.location.x == x && it.location.y == y }

    fun getLastId(): Int {
        var lastId = 0
        for (c in creatures) if (c.id > lastId) lastId = c.id
        return lastId
    }

    fun getInformation(): String {
        val sb = StringBuilder()
        for (c in creatures) {
            sb.append(c.getChar())
        }
        sb.append("\n")
        sb.append("width: $mapWidth\n")
        sb.append("height: $mapHeight\n")
        sb.append("creature number: ${creatures.size}\n")
        sb.append("creatures: \n")
        for (c in creatures) {
            sb.append("  ${c.info()}\n")
        }
        return sb.toString()
    }

    fun draw() {
        val sb = StringBuilder()
        sb.append("Village map:\n")
        // draw creatures in map(w=mapWidth, h=mapHeight), x from let to right, y from bottom to top
        for (y in mapHeight - 1 downTo 0) {
            for (x in 0 until mapWidth) {
                val c = getCreature(x, y)
                if (c != null) {
                    sb.append(c.getChar())
                } else {
                    sb.append('.')
                }
            }
            sb.append('\n')
        }
        creatures.forEach {
            sb.append("${it.name}${it.getStrLocation()} ")
        }
        sb.append("\n")
        print(sb.toString())
    }

    fun getCreatures(): List<Creature> = creatures.toList()
}