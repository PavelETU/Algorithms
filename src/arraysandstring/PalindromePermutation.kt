package arraysandstring

import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test

class PalindromePermutation {
    // Returns if the string is permutation of palindrome
    private fun isPalindromePermutation(input: String) =
            input
                    .toLowerCase()
                    .replace(Regex("[-,.!? ']"), "")
                    .run {
                        filter { (this.count { it2 -> it == it2 } % 2) != 0 }
                    }
                    .toSortedSet().size <= 1


    @Test
    fun testAlgorithm() {
        Assert.assertThat(isPalindromePermutation("tT"), CoreMatchers.`is`(true))
        Assert.assertThat(isPalindromePermutation("Tact Coa"), CoreMatchers.`is`(true))
        Assert.assertThat(isPalindromePermutation("abcabc"), CoreMatchers.`is`(true))
        Assert.assertThat(isPalindromePermutation("a b c a b cv"), CoreMatchers.`is`(true))
        Assert.assertThat(isPalindromePermutation("abcabcvp"), CoreMatchers.`is`(false))
        Assert.assertThat(isPalindromePermutation("M o M"), CoreMatchers.`is`(true))
        Assert.assertThat(isPalindromePermutation("d A d"), CoreMatchers.`is`(true))
        Assert.assertThat(isPalindromePermutation("d A d"), CoreMatchers.`is`(true))
        Assert.assertThat(isPalindromePermutation("Eva, Can I Stab Bats In A Cave?"), CoreMatchers.`is`(true))
        Assert.assertThat(isPalindromePermutation("!!!Eva, Can I S??t'''ab Ba...ts In A Cave?"), CoreMatchers.`is`(true))
        Assert.assertThat(isPalindromePermutation("A Man, A Plan, A Canal-Panama!"), CoreMatchers.`is`(true))
        Assert.assertThat(isPalindromePermutation("A , A Plan, A Canal-Panama!"), CoreMatchers.`is`(false))
        Assert.assertThat(isPalindromePermutation("Madam In Eden, I'm Adam"), CoreMatchers.`is`(true))
        Assert.assertThat(isPalindromePermutation("Madam  Eden, I'm Adam"), CoreMatchers.`is`(false))
    }


}