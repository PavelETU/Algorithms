package arraysandstring

import org.junit.Test
import kotlin.math.abs
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StringIsOneEditAway {

    // Three edits are possible - remove symbol, insert symbol and replace symbol.
    // Detect if string is less or 1 edit away of another
    private fun stringIsOneEditAwayMoreFunctionalStyle(first: String, second: String): Boolean {
        if (first == second) return true
        if (abs(first.length - second.length) > 1) return false
        return first.partitionIntoTwoByUsingPredicateAsDelimiter { index, char -> char != second.getOrNull(index) }.let {
            when {
                first.length == second.length -> it.first + second[it.first.length] + it.second.substring(1)
                first.length > second.length -> it.first + it.second.substring(1)
                else -> it.first + second[it.first.length] + it.second
            }
        } == second
    }

    private inline fun CharSequence.partitionIntoTwoByUsingPredicateAsDelimiter(predicate: (index: Int, Char) -> Boolean): Pair<String, String> {
        var index = 0
        val first = StringBuilder()
        val second = StringBuilder()
        for (item in this) {
            if (predicate(index++, item)) {
                second.append(substring(index - 1, length))
                break
            } else {
                first.append(item)
            }
        }
        return Pair(first.toString(), second.toString())
    }

    @Test
    fun someFunTest() {
        assertTrue { stringIsOneEditAwayMoreFunctionalStyle("Here", "Hee") }
        assertTrue { stringIsOneEditAwayMoreFunctionalStyle("Here", "Here") }
        assertTrue { stringIsOneEditAwayMoreFunctionalStyle("Hee", "Here") }
        assertTrue { stringIsOneEditAwayMoreFunctionalStyle("Here", "Hehe") }
        assertFalse { stringIsOneEditAwayMoreFunctionalStyle("ere", "Hehe") }
        assertFalse { stringIsOneEditAwayMoreFunctionalStyle("Here", "He") }
        assertFalse { stringIsOneEditAwayMoreFunctionalStyle("He", "Here") }
        assertFalse { stringIsOneEditAwayMoreFunctionalStyle("H  ere", "Here") }
    }

    @Test
    fun verifySplitStringBehavior() {
        assertEquals(Pair("ABC", "DFABC"), "ABCDFABC".partitionIntoTwoByUsingPredicateAsDelimiter { _, char -> char == 'D' })
        assertEquals(Pair("A", "BCDFABC"), "ABCDFABC".partitionIntoTwoByUsingPredicateAsDelimiter { index, char -> char != "A".getOrNull(index) })
        assertEquals(Pair("A", "BCDFABC"), "ABCDFABC".partitionIntoTwoByUsingPredicateAsDelimiter { index, char -> char != "A".getOrNull(index) })
        assertEquals(Pair("", "ABCDFABC"), "ABCDFABC".partitionIntoTwoByUsingPredicateAsDelimiter { index, char -> char != "".getOrNull(index) })
        assertEquals(Pair("ABCDFABC", ""), "ABCDFABC".partitionIntoTwoByUsingPredicateAsDelimiter { index, char -> char != "ABCDFABC".getOrNull(index) })
    }

    // Three edits are possible - remove symbol, insert symbol and replace symbol.
    // Detect if string is less or 1 edit away of another
    private fun stringIsOneEditAway(first: String, second: String): Boolean {
        return when {
            first.length - second.length == 1 -> checkIfOneSymbolFromFirstRemove(first, second)
            second.length - first.length == 1 -> checkIfOneSymbolFromFirstRemove(second, first)
            first.length == second.length -> checkSameLengthStrings(first, second)
            else -> false
        }
    }

    // first.length == second.length - 1
    private fun checkIfOneSymbolFromFirstRemove(first: String, second: String): Boolean {
        var j = 0
        for (i in 0 until second.length) {
            if (j == first.length) return false
            if (first[j] != second[i]) {
                if (j + 1 == first.length) return false
                if (first[j + 1] != second[i]) {
                    return false
                }
                j++
            }
            j++
        }
        return true
    }

    private fun checkSameLengthStrings(first: String, second: String): Boolean {
        if (first == second) return true
        var count = 0
        for (i in 0 until first.length) {
            if (first[i] != second[i]) {
                count++
            }
            if (count > 1) return false
        }
        return true
    }

    @Test
    fun someTest() {
        assertTrue { stringIsOneEditAway("Here", "Hee") }
        assertTrue { stringIsOneEditAway("Here", "Here") }
        assertTrue { stringIsOneEditAway("Hee", "Here") }
        assertTrue { stringIsOneEditAway("Here", "Hehe") }
        assertFalse { stringIsOneEditAway("ere", "Hehe") }
        assertFalse { stringIsOneEditAway("Here", "He") }
        assertFalse { stringIsOneEditAway("He", "Here") }
        assertFalse { stringIsOneEditAway("H  ere", "Here") }
    }

}
