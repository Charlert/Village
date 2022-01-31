package charlert.village

import charlert.village.creature.action.VillagerAction
import charlert.village.creature.action.WorldAction
import charlert.village.event.EventController
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType


fun main() {
    val eventController = EventController()
    var userInput: String
    while (true) {
        // 作用于Villager
        val letParser = ArgParser("let")
        val selectedVillager by letParser.argument(
            type = ArgType.Int, fullName = "village", description = "Choose a village"
        )
        val villagerAction by letParser.argument(
            type = ArgType.Choice<VillagerAction>(),
            fullName = "action",
            description = "The action what the object should do"
        )
        val targetCreature by letParser.option(
            type = ArgType.Int, fullName = "target", shortName = "t", description = "The target"
        )
        // 作用于World
        val plzParser = ArgParser("plz")
        val worldAction by plzParser.argument(
            type = ArgType.Choice<WorldAction>(), fullName = "action",
            description = "The action what the world should do"
        )

        print("--> ")
        userInput = readln()
        val args = userInput.split("\\s+".toRegex()).toTypedArray()
        if (args.size <= 1) continue
        when (args[0]) {
            "let" -> {
                letParser.parse(args.sliceArray(1 until args.size))
                if (villagerAction == VillagerAction.kill) {
                    targetCreature?.let { eventController.run(villagerAction, selectedVillager, it) }
                } else {
                    eventController.run(villagerAction, selectedVillager)
                }
            }
            "plz" -> {
                plzParser.parse(args.sliceArray(1 until args.size))
                eventController.run(worldAction)
            }
        }
    }
//    val parser = ArgParser("p")
//    val i by parser.option(type = ArgType.String, fullName = "hghag", shortName = "i")
//
//    val userInput = readln()
//    val args = userInput.split("\\s+".toRegex()).toTypedArray()
//    parser.parse(args.sliceArray(1 until args.size))
//    println(i is String)
}