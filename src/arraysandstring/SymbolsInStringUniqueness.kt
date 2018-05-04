package arraysandstring

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Test {

    private fun testAlgorithm(objectToTest: SymbolsInStringUniqueness) {
        assertThat(objectToTest.isAllSymbolsUnique("abcdefg"), `is`(true))
        assertThat(objectToTest.isAllSymbolsUnique("abcddefg"), `is`(false))
        assertThat(objectToTest.isAllSymbolsUnique("abca"), `is`(false))
        assertThat(objectToTest.isAllSymbolsUnique("abcc"), `is`(false))
        assertThat(objectToTest.isAllSymbolsUnique(""), `is`(true))
        assertThat(objectToTest.isAllSymbolsUnique("ABCabc"), `is`(true))
        assertThat(objectToTest.isAllSymbolsUnique("ABCabc", true), `is`(false))
    }

    @Test
    fun uniquenessOfN2Algorithm() {
        testAlgorithm(SymbolsInStringUniqueness(uniquenessWithNNApproach))
    }

    @Test
    fun uniquenessOfNAlgorithm() {
        testAlgorithm(SymbolsInStringUniqueness(uniquenessWithNApproach))
    }

    @Test
    fun uniquenessOfNAlgorithmForOnlyEnglSymbols() {
        testAlgorithm(SymbolsInStringUniqueness(uniquenessWithNApproachForEnglishAlphabet))
    }

    @Test(expected = RuntimeException::class)
    fun uniquenessOfNAlgorithmForOnlyEnglSymbolsThrowsExceptions() {
        SymbolsInStringUniqueness(uniquenessWithNApproachForEnglishAlphabet).isAllSymbolsUnique("abcdefgiy86")
    }

}

class SymbolsInStringUniqueness(private val strategy: (String, Boolean) -> Boolean) {
    fun isAllSymbolsUnique(input: String, treatDifferentCasesAsOneCharacter: Boolean = false): Boolean {
        return strategy.invoke(input, treatDifferentCasesAsOneCharacter)
    }
}

val uniquenessWithNNApproach = fun(input: String, treatDifferentCasesAsOneCharacter: Boolean): Boolean {
    var inputToProcess = input
    if (treatDifferentCasesAsOneCharacter) {
        inputToProcess = input.toLowerCase()
    }
    for (i in 0..(inputToProcess.length - 2)) {
        for (j in (i + 1)..(inputToProcess.length - 1)) {
            if (inputToProcess[i] == inputToProcess[j]) return false
        }
    }
    return true
}

val uniquenessWithNApproach = fun(input: String, treatDifferentCasesAsOneCharacter: Boolean): Boolean {
    var inputToProcess = input
    if (treatDifferentCasesAsOneCharacter) {
        inputToProcess = input.toLowerCase()
    }
    val flags = HashMap<Int, Boolean>()
    for (c in inputToProcess) {
        if (flags[c.toInt()] == true) {
            return false
        }
        flags[c.toInt()] = true
    }
    return true
}

val uniquenessWithNApproachForEnglishAlphabet = fun(input: String, treatDifferentCasesAsOneCharacter: Boolean): Boolean {
    var flagsLowerCase = 0
    var flagsUpperCase = 0
    var inputToProcess = input
    if (treatDifferentCasesAsOneCharacter) {
        inputToProcess = input.toLowerCase()
    }
    for (c in inputToProcess) {
        if (c.toInt() >= 'A'.toInt() && c.toInt() <= 'Z'.toInt()) {
            val relativeNumberOfChar = c.toInt() - 'A'.toInt()
            if ((flagsUpperCase and (1 shl relativeNumberOfChar)) != 0) {
                return false
            }
            flagsUpperCase = flagsUpperCase or (1 shl relativeNumberOfChar)
        } else if (c.toInt() >= 'a'.toInt() && c.toInt() <= 'z'.toInt()) {
            val relativeNumberOfChar = c.toInt() - 'A'.toInt()
            if ((flagsLowerCase and (1 shl relativeNumberOfChar)) != 0) {
                return false
            }
            flagsLowerCase = flagsLowerCase or (1 shl relativeNumberOfChar)
        } else {
            throw RuntimeException("Only For English alphabet")
        }

    }
    return true
}
