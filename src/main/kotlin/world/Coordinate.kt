package charlert.village.world

import kotlin.math.sqrt

class Coordinate(var x: Int, var y: Int) {
    fun getDistance(c: Coordinate): Float {
        val a = x.toFloat() - c.x.toFloat()
        val b = y.toFloat() - c.y.toFloat()
        return sqrt(a * a + b * b)
    }
}