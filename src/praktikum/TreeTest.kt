package praktikum

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TreeTest {

    @Test // Nach dem Übungsblatt, Programmierstil??
    fun `an EmptyTree should be empty`() {
        val tree = emptyTree<Int>()
        assertEquals(true, tree.isEmpty())
    }

    @Test
    fun `an EmptyTree should be empty2`() {
        val tree = emptyTree<Int>()
        assertTrue(tree.isEmpty())
    }

    @Test
    fun `a treeNode should not be empty`() {
        val tree = treeNode(10, emptyTree(), emptyTree())
        assertFalse(tree.isEmpty())
    }

    @Test
    fun `an EmptyTree should have size 0`() {
        val tree = emptyTree<Int>()
        assertEquals(tree.size(), 0)
    }

    @Test
    fun `a treeNode should have size 1`() {
        val tree = treeNode(10)
        assertEquals(tree.size(), 1)
    }

    @Test
    fun `a tree with 2 treeNodes should have size 2`() {
        var tree = treeNode(10)
        tree = tree.addValue(9, Int::compareTo)
        assertEquals(tree.size(), 2)
    }

    @Test
    fun `a tree with 3 treeNodes should have size 3`() {
        var tree = treeNode(10)
        tree = tree.addValue(9) { a, b -> a.compareTo(b) }
        tree = tree.addValue(8) { a, b -> a.compareTo(b) }
        assertEquals(tree.size(), 3)
    }

    @Test
    fun `a tree with 4 treeNodes should have size 4`() {
        var tree = treeNode(10)
        tree = tree.addValue(9, Int::compareTo)
        tree = tree.addValue(8, Int::compareTo)
        tree = tree.addValue(11, Int::compareTo)
        assertEquals(tree.size(), 4)
    }

    @Test
    fun `a tree with 5 treeNodes should have size 5`() {
        var tree = treeNode(10)
        tree = tree.addValue(9) { a, b -> a.compareTo(b) }
        tree = tree.addValue(8) { a, b -> a.compareTo(b) }
        tree = tree.addValue(11) { a, b -> a.compareTo(b) }
        tree = tree.addValue(12) { a, b -> a.compareTo(b) }
        assertEquals(tree.size(), 5)
    }

    @Test
    fun `an emptyTree should have depth of 0`() {
        val tree = emptyTree<Int>()
        assertEquals(tree.depth(), 0)
    }

    @Test
    fun `tree with 1 treeNode should have depth of 1`() {
        val tree = treeNode(10)
        assertEquals(tree.depth(), 1)
    }

    @Test
    fun `tree with 2 treeNodes on 2 levels should have depth of 2`() {
        var tree = treeNode(10)
        tree = tree.addValue(9, Int::compareTo)
        assertEquals(tree.depth(), 2)
    }

    @Test
    fun `tree with 3 treeNodes on 3 levels should have depth of 3`() {
        var tree = treeNode(10)
        tree = tree.addValue(9, Int::compareTo)
        tree = tree.addValue(8, Int::compareTo)
        assertEquals(tree.depth(), 3)
    }

    @Test
    fun `tree with 3 treeNodes on 2 levels should have depth of 2`() {
        var tree = treeNode(10) // Ein Baum mit 2 Ästen
        tree = tree.addValue(9) { a, b -> a.compareTo(b) }
        tree = tree.addValue(11) { a, b -> a.compareTo(b) }
        assertEquals(tree.depth(), 2)
    }

    @Test
    fun `a tree should contain an added value`() {
        var tree = treeNode(10)
        tree = tree.addValue(9, Int::compareTo)
        assertTrue(tree.contains(9, Int::compareTo))
    }

    @Test
    fun `a transformed tree should not contain old values`() {
        var tree = treeNode(10)
        tree = tree.addValue(9, Int::compareTo)
        tree = tree.addValue(11, Int::compareTo)
        tree = tree.map { it + 1 }
//        tree.processPreOrder { print("$it ") }
        assertFalse(tree.contains(9, Int::compareTo))
    }
}