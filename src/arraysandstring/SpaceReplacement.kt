package arraysandstring

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertNull
import org.junit.Assert.assertThat
import org.junit.Test

/*
Replace all spaces in a string with specified string. Making change in place.
Size of important part of the string is specified, and size of input is equal to output size
 */
class SpaceReplacement {

    @Test
    fun testAlgorithm() {
        assertThat(replaceSpacesWithSymbols("Mr Unknown Person    ".toCharArray(), 17, "HEY"),
                `is`("MrHEYUnknownHEYPerson".toCharArray()))
    }

    private fun replaceSpacesWithSymbols(input: CharArray, stringSize: Int, charactersToBeInserted: String): CharArray {
        val queue = MyQueue<Char>(null)
        for (i in 0 until stringSize) {
            val c = input[i]
            if (c == ' ') {
                queue.addElements(charactersToBeInserted.toCharArray().toTypedArray())
            }
            if (queue.peek() != null) {
                if (c != ' ') {
                    queue.add(c)
                }
                input[i] = queue.get()!!
            }
        }
        var pointer = stringSize
        while (queue.peek() != null) {
            input[pointer] = queue.get()!!
            pointer++
        }
        return input
    }

    @Test
    fun testQueue() {
        val queueToTest = MyQueue(1)
        queueToTest.add(2)
        queueToTest.add(3)
        queueToTest.add(4)
        queueToTest.add(5)
        assertThat(queueToTest.get(), `is`(1))
        assertThat(queueToTest.get(), `is`(2))
        assertThat(queueToTest.get(), `is`(3))
        queueToTest.add(0)
        assertThat(queueToTest.get(), `is`(4))
        assertThat(queueToTest.get(), `is`(5))
        assertThat(queueToTest.get(), `is`(0))
        assertNull(queueToTest.get())
    }

    @Test
    fun testQueueAddAll() {
        val queueToTest = MyQueue<Int>(null)
        queueToTest.addElements(Array(5) { it })
        assertThat(queueToTest.get(), `is`(0))
        assertThat(queueToTest.get(), `is`(1))
        assertThat(queueToTest.get(), `is`(2))
        assertThat(queueToTest.get(), `is`(3))
        assertThat(queueToTest.get(), `is`(4))
        assertNull(queueToTest.get())
    }

    open class MyQueue<T>(var element: T?) {
        var nextElement: MyQueue<T>? = null
        fun peek() = element
        fun get(): T? {
            val elementToReturn = element
            element = nextElement?.element
            nextElement = nextElement?.nextElement
            return elementToReturn
        }

        fun add(elementToAdd: T) {
            if (element == null) {
                element = elementToAdd
                return
            }
            var elementToAddToo = this
            while (elementToAddToo.nextElement != null) {
                elementToAddToo = elementToAddToo.nextElement!!
            }
            elementToAddToo.nextElement = MyQueue(elementToAdd)
        }

        fun addElements(elements: Array<T>) {
            var elementToAddToo = this
            if (elementToAddToo.element != null) {
                while (elementToAddToo.nextElement != null) {
                    elementToAddToo = elementToAddToo.nextElement!!
                }
            }
            for (elementToAdd in elements) {
                if (elementToAddToo.element == null) {
                    elementToAddToo.element = elementToAdd
                } else {
                    elementToAddToo.nextElement = MyQueue(elementToAdd)
                    elementToAddToo = elementToAddToo.nextElement!!
                }
            }
        }
    }

}