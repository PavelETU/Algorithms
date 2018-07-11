package arraysandstring

import org.junit.Test
import kotlin.test.assertEquals

class StringCompression {

    private fun compressStringMoreFun(input: String) =
            input.toDistinctList()
                    .map { string: String -> string[0].toString() + string.length }
                    .fold("") { acc, s -> acc + s }
                    .takeUnless { input.length * 2 == it.length } ?: input


    private fun String.toDistinctList(): List<String> {
        val result = ArrayList<String>()
        if (length == 0) return result
        val stringBuilder = StringBuilder().append(this[0])
        for (i in 1 until length) {
            if (this[i] == this[i - 1]) {
                stringBuilder.append(this[i])
            } else {
                result.add(stringBuilder.toString())
                stringBuilder.setLength(0)
                stringBuilder.append(this[i])
            }
        }
        result.add(stringBuilder.toString())
        return result
    }

    @Test
    fun testStringCompressionMoreFun() {
        assertEquals("a2b2c1", compressStringMoreFun("aabbc"))
        assertEquals("abc", compressStringMoreFun("abc"))
        assertEquals("a2b2c1a5", compressStringMoreFun("aabbcaaaaa"))
        assertEquals("", compressStringMoreFun(""))
    }

    private fun compressString(input: String): String {
        if (input.isEmpty()) return ""
        val resultString = StringBuffer()
        var count = 1
        for (i in 1 until input.length) {
            if (input[i - 1] == input[i]) {
                count++
            } else {
                resultString.append(input[i - 1]).append(count)
                count = 1
            }
        }
        resultString.append(input[input.lastIndex]).append(count)
        return if (resultString.length < input.length * 2) resultString.toString() else input
    }

    @Test
    fun testStringCompression() {
        assertEquals("a2b2c1", compressString("aabbc"))
        assertEquals("abc", compressString("abc"))
        assertEquals("a2b2c1a5", compressString("aabbcaaaaa"))
        assertEquals("", compressStringMoreFun(""))
    }
}
