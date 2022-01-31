package charlert.village.god

class Announcer(private val sender: String) {
    fun send(text: String) {
        println("$sender: $text")
    }
}