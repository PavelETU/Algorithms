package dynamicprogramming

import org.junit.Test
import kotlin.test.assertEquals

// It actually should be triple step but I'm too much into Radiohead for that
class FifteenStep {
    private fun waysToStep(n: Int): Int {
        return when(n) {
            1 -> 1
            2 -> 2
            3 -> 4
            else -> waysToStep(n - 1) + waysToStep(n - 2) + waysToStep(n - 3) + 3
        }
    }

    @Test
    fun baseCases() {
        assertEquals(1, waysToStep(1))
        assertEquals(2, waysToStep(2))
        assertEquals(4, waysToStep(3))
    }
}