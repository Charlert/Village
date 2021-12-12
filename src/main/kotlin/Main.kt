package charlert.village

import charlert.village.event.EventController

fun main() {
    val ec = EventController()
    ec.villagerFactory.create()
    val v2 = ec.villagerFactory.create()
    ec.allRun("hello")
    ec.run("die", v2)
    ec.allRun("hello")
}