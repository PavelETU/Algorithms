package arraysandstring

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class SymbolsInStringUniqueness {

    @Test
    fun uniquenessOfN2Algorithm() {
        assertThat(isAllSymbolsUnique("abcdefg"), `is`(true))
        assertThat(isAllSymbolsUnique("abca"), `is`(false))
        assertThat(isAllSymbolsUnique("abcc"), `is`(false))
        assertThat(isAllSymbolsUnique(""), `is`(true))
        assertThat(isAllSymbolsUnique("ABCabc"), `is`(true))
        assertThat(isAllSymbolsUnique("ABCabc", true), `is`(false))
    }

    @Test
    fun uniquenessOfNAlgorithm() {
        assertThat(isAllSymbolsUniqueInNTime("abcdefg"), `is`(true))
        assertThat(isAllSymbolsUniqueInNTime("abca"), `is`(false))
        assertThat(isAllSymbolsUniqueInNTime("abcc"), `is`(false))
        assertThat(isAllSymbolsUniqueInNTime(""), `is`(true))
        assertThat(isAllSymbolsUniqueInNTime("ABCabc"), `is`(true))
        assertThat(isAllSymbolsUniqueInNTime("ABCabc", true), `is`(false))
    }

    @Test
    fun uniquenessOfNAlgorithmForOnlyEnglSymbols() {
        assertThat(isAllSymbolsUniqueInNTimeForAmericanLettersOnly("abcdefg"), `is`(true))
        assertThat(isAllSymbolsUniqueInNTimeForAmericanLettersOnly("abca"), `is`(false))
        assertThat(isAllSymbolsUniqueInNTimeForAmericanLettersOnly("abcc"), `is`(false))
        assertThat(isAllSymbolsUniqueInNTimeForAmericanLettersOnly(""), `is`(true))
        assertThat(isAllSymbolsUniqueInNTimeForAmericanLettersOnly("ABCabc"), `is`(false))
    }

    @Test(expected = RuntimeException::class)
    fun uniquenessOfNAlgorithmForOnlyEnglSymbolsThrowsExceptions() {
        isAllSymbolsUniqueInNTimeForAmericanLettersOnly("abcdefgiy86")
    }

    private fun isAllSymbolsUnique(input: String, treatDifferentCasesAsOneCharacter: Boolean = false): Boolean {
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

    private fun isAllSymbolsUniqueInNTime(input: String, treatDifferentCasesAsOneCharacter: Boolean = false) : Boolean {
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

    private fun isAllSymbolsUniqueInNTimeForAmericanLettersOnly(input: String) : Boolean {
        var flags = 0
        val inputToProcess = input.toLowerCase()
        for (c in inputToProcess) {
            val relativeNumberOfChar = c.toInt() - 'a'.toInt()
            if (relativeNumberOfChar > 25 || relativeNumberOfChar < 0) {
                throw RuntimeException("Only For English alphabet")
            }
            if ((flags and (1 shl relativeNumberOfChar)) != 0) {
                return false
            }
            flags = flags or (1 shl relativeNumberOfChar)
        }
        return true
    }


}