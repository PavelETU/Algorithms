package treesandgraphs

import org.junit.Test
import kotlin.test.assertEquals

class TwoNodesAreConnected {
    private fun notesAreConnected(graph: Graph<Int>, valueOfNodeOne: Int, valueOfNodeTwo: Int): Boolean {
        val nodeOne = graph.findNode(valueOfNodeOne)
        val nodeTwo = graph.findNode(valueOfNodeTwo)
        throw RuntimeException("Not implemented yet")
    }

    private fun <T> Graph<T>.findNode(nodeToFind: T): Node<T>? {
        for (node in nodes) {
            if (node.value == nodeToFind) {
                return node
            }
        }
        return null
    }

    @Test
    fun testFindNodeInTheFirstLayer() {
        val firstNode = Node(8, null)
        val secondNode = Node(126, null)
        val thirdNode = Node(90753, null)
        val graph = Graph(listOf(firstNode, secondNode, thirdNode))

        assertEquals(firstNode, graph.findNode(8))
        assertEquals(secondNode, graph.findNode(126))
        assertEquals(thirdNode, graph.findNode(90753))
        assertEquals(null, graph.findNode(53))
    }
}



data class Graph<out T>(val nodes: List<Node<T>>)

data class Node<out T>(val value: T, val child: List<Node<T>>?)