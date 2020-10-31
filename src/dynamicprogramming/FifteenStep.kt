package dynamicprogramming

import org.junit.Test
import kotlin.test.assertEquals

// It actually should be triple step but I'm too much into Radiohead for that
class FifteenStep {
    private fun waysToStep(n: Int): Long {
        val counts: MutableMap<Int, Long> = mutableMapOf(1 to 1, 2 to 2, 3 to 4)
        for (i in 4..n) {
            counts[i] = counts[i - 1]!! + counts[i - 2]!! + counts[i - 3]!!
        }
        return counts[n]!!
    }

    @Test
    fun baseCases() {
        assertEquals(1, waysToStep(1))
        assertEquals(2, waysToStep(2))
        assertEquals(4, waysToStep(3))
        assertEquals(7, waysToStep(4))
    }
}