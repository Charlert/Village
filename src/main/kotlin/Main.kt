package charlert.village

import charlert.village.god.Staff
import net.sourceforge.argparse4j.helper.HelpScreenException
import net.sourceforge.argparse4j.inf.ArgumentParserException

class Main {
    companion object {
        private fun welcome() {
            println("Welcome to the Village of Charlert!")
            println("Enter 'help' for a list of commands.")
            println("Enter 'quit' to exit the game.")
            println()
        }

        private fun Staff.help() {
            try {
                let(arrayOf("--help"))
            } catch (e: ArgumentParserException) {
                println(e.message)
            }
            repeat(16) {
                print("-")
            }
            println()
            try {
                plz(arrayOf("--help"))
            } catch (e: ArgumentParserException) {
                println(e.message)
            }
            repeat(16) {
                print("-")
            }
            println()
            try {
                summon(arrayOf("--help"))
            } catch (e: ArgumentParserException) {
                println(e.message)
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            welcome()
            val staff = Staff()
            while (true) {
                staff.go()
                staff.world.draw()
                println()
                print("--> ")
                val userInput = readln().split(" ").toTypedArray()
                try {
                    when (userInput[0]) {
                        "let" -> staff.let(userInput.drop(1).toTypedArray())
                        "plz" -> staff.plz(userInput.drop(1).toTypedArray())
                        "summon" -> staff.summon(userInput.drop(1).toTypedArray())
                        "help" -> staff.help()
                        "test" -> staff.test()
                        "quit" -> return
                        else -> println("invalid command")
                    }
                } catch (_: HelpScreenException) {
                } catch (e: ArgumentParserException) {
                    println(e.message)
                }
            }
        }
    }
}

