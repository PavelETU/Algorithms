package arraysandstring

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/*
Check if one string permutation of other, i.e. all symbols of one string contains in other
*/
class StringIsPermutationOfAnother {

    @Test
    fun testPermutation() {
        assertThat(isSecondStringPermutationOfFirst("god", "dog"), `is`(true))
        assertThat(isSecondStringPermutationOfFirst("god", "dog "), `is`(false))
        assertThat(isSecondStringPermutationOfFirst("dba", "abc"), `is`(false))
        assertThat(isSecondStringPermutationOfFirst("gone", "oneg"), `is`(true))
        assertThat(isSecondStringPermutationOfFirst("bless", "lessb"), `is`(true))
        assertThat(isSecondStringPermutationOfFirst("bless", "leese"), `is`(false))
    }

    private fun isSecondStringPermutationOfFirst(first: String, second: String): Boolean {
        if (first.length != second.length) return false
        val firstStringInOrder = sortString(first.toCharArray())
        val secondStringInOrder = sortString(second.toCharArray())
        for (i in 0 until firstStringInOrder.size) {
            if (firstStringInOrder[i] != secondStringInOrder[i]) {
                return false
            }
        }
        return true
    }

    @Test
    fun testSortStringAlgorithm() {
        assertThat(sortString("".toCharArray()), `is`("".toCharArray()))
        assertThat(sortString(";".toCharArray()), `is`(";".toCharArray()))
        assertThat(sortString("Ny".toCharArray()), `is`("Ny".toCharArray()))
        assertThat(sortString("dYc".toCharArray()), `is`("Ycd".toCharArray()))
        assertThat(sortString("dYca".toCharArray()), `is`("Yacd".toCharArray()))
        assertThat(sortString("abczzzyyynnn".toCharArray()), `is`("abcnnnyyyzzz".toCharArray()))
        assertThat(sortString("aVbVcVzzzVMyaAyMynMnnAAAAAA".toCharArray()), `is`("AAAAAAAMMMVVVVaabcnnnyyyzzz".toCharArray()))
    }

    private fun sortString(stringToSort: CharArray): CharArray {
        if (stringToSort.size <= 1) return stringToSort
        val indexOfHalf = stringToSort.size / 2
        var firstHalf = CharArray(indexOfHalf)
        var secondHalf = CharArray(stringToSort.size - indexOfHalf)
        for (i in 0 until stringToSort.size) {
            if (i < indexOfHalf) {
                firstHalf[i] = stringToSort[i]
            } else {
                secondHalf[i - indexOfHalf] = stringToSort[i]
            }
        }
        firstHalf = sortString(firstHalf)
        secondHalf = sortString(secondHalf)
        return mergeStrings(firstHalf, secondHalf)
    }

    @Test
    fun testMergeStep() {
        assertThat(mergeStrings("c".toCharArray(), "a".toCharArray()), `is`("ac".toCharArray()))
        assertThat(mergeStrings("cf".toCharArray(), "ad".toCharArray()), `is`("acdf".toCharArray()))
        assertThat(mergeStrings("aeeeeeeeeeeeeeeeeeeeeee".toCharArray(), "ddddddd".toCharArray()), `is`("adddddddeeeeeeeeeeeeeeeeeeeeee".toCharArray()))
        assertThat(mergeStrings("ddddddd".toCharArray(), "aeeeeeeeeeeeeeeeeeeeeee".toCharArray()), `is`("adddddddeeeeeeeeeeeeeeeeeeeeee".toCharArray()))
    }

    private fun mergeStrings(first: CharArray, second: CharArray): CharArray {
        val result = CharArray(first.size + second.size)
        var i = 0
        var j = 0
        while (i + j < first.size + second.size) {
            if (j == second.size) {
                result[i + j] = first[i]
                i++
            } else if (i == first.size) {
                result[i + j] = second[j]
                j++
            } else {
                if (first[i] > second[j]) {
                    result[i + j] = second[j]
                    j++
                } else {
                    result[i + j] = first[i]
                    i++
                }
            }
        }
        return result
    }

}