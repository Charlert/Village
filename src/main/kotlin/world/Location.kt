package charlert.village.world

import kotlin.math.sqrt

class Location(var x: Int, var y: Int) {
    fun getDistance(c: Location): Float {
        val a = x.toFloat() - c.x.toFloat()
        val b = y.toFloat() - c.y.toFloat()
        return sqrt(a * a + b * b)
    }
}