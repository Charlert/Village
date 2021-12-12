package charlert.village

import charlert.village.event.EventController

fun main() {
    val ec = EventController()
    ec.villagerFactory.create()
    while (true) {
        print("--> ")
        val cmd = readln()
        when (cmd) {
            "run" -> {
                print("--> (run) target id = ")
                val id = readln().toInt()
                print("--> (run) command = ")
                val cmd2 = readln()
                ec.run(cmd2, id)
            }
            "all run" -> {
                print("--> (all run) command = ")
                val cmd2 = readln()
                ec.allRun(cmd2)
            }
            "world run" -> {
                print("--> (world run) command = ")
                val cmd2 = readln()
                ec.worldRun(cmd2)
            }
            else -> {
                println("error: undefined cmd \"$cmd\"")
            }
        }
    }
}