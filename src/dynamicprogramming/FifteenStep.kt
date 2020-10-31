package dynamicprogramming

import org.junit.Test
import kotlin.test.assertEquals

// It actually should be triple step but I'm too much into Radiohead for that
class FifteenStep {
    private fun waysToStep(n: Int): Int {
        val counts: MutableMap<Int, Int> = mutableMapOf(1 to 1, 2 to 2, 3 to 4)
        for (i in 4..n) {
            counts[i] = counts[n - 1]!! + counts[n - 2]!! + counts[n - 3]!! + 3
        }
        return counts[n]!!
    }

    @Test
    fun baseCases() {
        assertEquals(1, waysToStep(1))
        assertEquals(2, waysToStep(2))
        assertEquals(4, waysToStep(3))
        assertEquals(10, waysToStep(4))
    }
}