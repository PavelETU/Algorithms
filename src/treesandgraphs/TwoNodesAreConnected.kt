package treesandgraphs

import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class TwoNodesAreConnected {
    private fun <T> nodesAreConnected(graph: Graph<T>, valueOfNodeOne: T, valueOfNodeTwo: T): Boolean {
        if (valueOfNodeOne == valueOfNodeTwo) return true
        val nodeOne = graph.findNode(valueOfNodeOne) ?: return false
        val nodeTwo = graph.findNode(valueOfNodeTwo) ?: return false
        val queue: Queue<Node<T>> = LinkedList(nodeOne.child ?: emptyList())
        val queue2: Queue<Node<T>> = LinkedList(nodeTwo.child ?: emptyList())
        if (nodeIsInTheChildOfNode(valueOfNodeTwo, queue)) return true
        if (nodeIsInTheChildOfNode(valueOfNodeOne, queue2)) return true
        return false
    }

    private fun <T> nodeIsInTheChildOfNode(valueToFind: T, queueOfValues: Queue<Node<T>>): Boolean {
        while (queueOfValues.isNotEmpty()) {
            val node = queueOfValues.poll()
            if (node.value == valueToFind) {
                return true
            }
            if (node.child != null) queueOfValues.addAll(node.child)
        }
        return false
    }

    @Test
    fun testNodesInDeeperLayer() {
        val firstNode1 = Node(8, null)
        val secondNode1 = Node(126, null)
        val firstNode2 = Node(9, listOf(Node(10, listOf(Node(11, null), secondNode1))))
        val secondNode2 = Node(127, null)
        val thirdNode1 = Node(90753, null)
        val thirdNode2 = Node(90754, listOf(Node(90755, listOf(Node(90756, listOf(secondNode2, Node(90757, null)))))))
        val graph = Graph(listOf(Node(88, listOf(firstNode1, firstNode2)),
                Node(126126, listOf(secondNode1, secondNode2)),
                Node(9075390753, listOf(thirdNode1, thirdNode2))))

        assertTrue(nodesAreConnected(graph, 9, 126))
        assertTrue(nodesAreConnected(graph, 126, 9))
        assertTrue(nodesAreConnected(graph, 90754, 127))
        assertTrue(nodesAreConnected(graph, 127, 90754))
    }

    @Test
    fun testNodesInTheFirstLayer() {
        val firstNode1 = Node(8, null)
        val secondNode1 = Node(126, null)
        val firstNode2 = Node(9, listOf(secondNode1))
        val secondNode2 = Node(127, null)
        val thirdNode1 = Node(90753, null)
        val thirdNode2 = Node(90754, listOf(secondNode2))
        val graph = Graph(listOf(Node(88, listOf(firstNode1, firstNode2)),
                Node(126126, listOf(secondNode1, secondNode2)),
                Node(9075390753, listOf(thirdNode1, thirdNode2))))

        assertTrue(nodesAreConnected(graph, 9, 126))
        assertTrue(nodesAreConnected(graph, 126, 9))
        assertTrue(nodesAreConnected(graph, 90754, 127))
        assertTrue(nodesAreConnected(graph, 127, 90754))
    }

    @Test
    fun testEdgeCaseWithSameNodes() {
        val firstNode1 = Node(8, null)
        val graph = Graph(listOf(Node(88, listOf(firstNode1))))

        assertTrue(nodesAreConnected(graph, 8, 8))
    }

    @Test
    fun testIfNodesAreNotInGraphTheyAreNotConnected() {
        val firstNode1 = Node(8, null)
        val firstNode2 = Node(9, null)
        val secondNode1 = Node(126, null)
        val secondNode2 = Node(127, null)
        val thirdNode1 = Node(90753, null)
        val thirdNode2 = Node(90754, null)
        val graph = Graph(listOf(Node(88, listOf(firstNode1, firstNode2)),
                Node(126126, listOf(secondNode1, secondNode2)),
                Node(9075390753, listOf(thirdNode1, thirdNode2))))

        assertFalse(nodesAreConnected(graph, 1, 8))
        assertFalse(nodesAreConnected(graph, 8, 1))
    }

    private fun <T> Graph<T>.findNode(nodeToFind: T): Node<T>? {
        val queue: Queue<Node<T>> = LinkedList()
        for (node in nodes) {
            if (node.value == nodeToFind) {
                return node
            }
            queue.add(node)
        }
        while(queue.isNotEmpty()) {
            val nodesToIterate = queue.poll().child ?: continue
            for (node in nodesToIterate) {
                if (node.value == nodeToFind) {
                    return node
                }
                queue.add(node)
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

    @Test
    fun testFindNodeInTheSecondLayer() {
        val firstNode1 = Node(8, null)
        val firstNode2 = Node(9, null)
        val secondNode1 = Node(126, null)
        val secondNode2 = Node(127, null)
        val thirdNode1 = Node(90753, null)
        val thirdNode2 = Node(90754, null)
        val graph = Graph(listOf(Node(88, listOf(firstNode1, firstNode2)),
                Node(126126, listOf(secondNode1, secondNode2)),
                Node(9075390753, listOf(thirdNode1, thirdNode2))))

        assertEquals(firstNode1, graph.findNode(8))
        assertEquals(firstNode2, graph.findNode(9))
        assertEquals(secondNode1, graph.findNode(126))
        assertEquals(secondNode2, graph.findNode(127))
        assertEquals(thirdNode1, graph.findNode(90753))
        assertEquals(thirdNode2, graph.findNode(90754))
        assertEquals(null, graph.findNode(1))
    }

    @Test
    fun testFindNodeInDeepLevels() {
        val firstNode = Node(8, null)
        val secondNode = Node(126, null)
        val thirdNode = Node(90753, null)
        val graph = Graph(listOf(Node(88,
                listOf(Node(888, listOf(Node(8888, listOf(Node(88888, listOf(firstNode)))))))),
                Node(126126, listOf(Node(127, listOf(secondNode)))),
                Node(9075390753, listOf(Node(97, listOf(Node(98, listOf(Node(99,
                        listOf(Node(100, listOf(Node(101,
                                listOf(Node(102, listOf(Node(103,
                                        listOf(Node(103, listOf(Node(104,
                                                listOf(Node(105, listOf(thirdNode))))))))))))))))))))))))

        assertEquals(firstNode, graph.findNode(8))
        assertEquals(secondNode, graph.findNode(126))
        assertEquals(thirdNode, graph.findNode(90753))
        assertEquals(null, graph.findNode(1))
    }
}



data class Graph<out T>(val nodes: List<Node<T>>)

data class Node<out T>(val value: T, val child: List<Node<T>>?)