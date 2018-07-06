package functionalprogramming

import arrow.core.Either
import arrow.core.Option
import arrow.core.Try
import arrow.core.getOrElse
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import java.util.*
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SomeFunctionalProgrammingStaff {

    private fun itDoesSomething(elements: List<String>): HashMap<String, Int> {
        var i = 0
        val results = hashMapOf<String, Int>()
        while (i < elements.size) {
            val element = results[elements[i]]
            if (element != null) {
                results[elements[i]] = element + 1
            } else {
                results[elements[i]] = 1
            }
            i++
        }
        return results
    }

    @Test
    fun testItDoesSomething() {
        assertThat(itDoesSomething(listOf("Here", "We", "Here", "Go")),
                `is`(hashMapOf(Pair("Here", 2), Pair("Go", 1), Pair("We", 1))))
    }

    private fun itDoesSomethingAlso(elements: List<String>): List<Pair<String, Int>> {
        return elements.groupBy { it }.map { Pair(it.key, it.value.count()) }
    }

    @Test
    fun testItDoesSomethingAlso() {
        assertThat(itDoesSomethingAlso(listOf("Here", "We", "Here", "Go")),
                `is`(listOf(Pair("Here", 2), Pair("We", 1), Pair("Go", 1))))
    }

    private fun higherOrder(stringToPrint : String, first: Int = 20, second: Int = 40, func: (Int, Int) -> Int) {
        println("$stringToPrint is ${func(first, second)}")
    }

    private fun someFun(first: Int, second: Int) = first + second

    private fun hosp(func: (Int) -> Int) {
        println("Function(2) result ${func(2)}")
    }
    
    private infix fun String.infixFun(value: String) = this == value

    @Test
    fun doStuff() {
        val numbers = 1..100

        numbers.filter { it % 5 == 0 }.map { it * 20 }.apply { println(this.size) }

        val st = "NO"
        val sec = "NO"
        println(st infixFun sec)
        higherOrder("Minus", func = fun(x, y) : Int {
            return x - y
        })
    }

    @Test
    fun doStuff2() {
        val numbers = 1..100

        numbers.forEach {
            if (it % 5 == 0) {
                // without @forEach "Hello" will never get printed
                return@forEach
            }
        }
        println("Hello")
    }

    data class GoodCustomer(val customerName: String, val customerPhone: String)

    @Test
    fun doStuffWithSingleton() {
        MyFirstSingletonInKotlin.callMySingletonMethod()
        println(MyFirstSingletonInKotlin.changeValue)
        println(oneSecondLater())
        assertFalse { MyFirstSingletonInKotlin.changeValue == oneSecondLater() }
    }

    private fun oneSecondLater(): Date {
        Thread.sleep(1000)
        return Date()
    }

    object NotApplicable

    fun getVATValue(country: String): Either<NotApplicable, Double> {
        return if (country != "ES") {
            Either.Left(NotApplicable)
        } else {
            Either.Right(3000.0)
        }
    }

    @Test
    fun firstArrowEncounter() {
        assertFalse { getVATValue("ES").isLeft() }
        assertTrue { getVATValue("US").isLeft() }
    }

    fun getSomeOrThrow(input : String) : Try<Int> {
        return try {
            val res = Integer.parseInt(input)
            Try.just(res)
        } catch (e: NumberFormatException) {
            Try.raise(e)
        }
    }

    @Test
    fun checkTry() {
        getSomeOrThrow("oaisfdj").getOrElse { println("Exception was thrown $it") }
    }

    @Test
    fun checkOption() {
        val option = Option.empty<String>()
        //option.
    }

}

object MyFirstSingletonInKotlin {
    fun callMySingletonMethod() {
        println("You called me, dude!")
    }

    val changeValue
        get() = Date()

}
