package praktikum
import java.lang.Integer.max


sealed class Tree<A> {
     fun isEmpty(): Boolean {
         return when(this) {
             is Node -> false
             else -> true
         }
     }
     fun size(): Int {
         return when(this) {
             is Node -> 1 + left.size() + right.size()
             is Empty -> 0
         }
     }

     fun depth(): Int {
         return when(this) {
             is Node -> 1 + max(left.depth(), right.depth())
             is Empty -> 0
         }
     }
     fun addValue(value: A, compare: (A, A) -> Int): Tree<A> {
         return when(this) {
             is Node -> when {
                 compare(value, this.value) == -1 -> Node(this.value, left.addValue(value, compare), right)
                 compare(value, this.value) == 1 -> Node (this.value, left, right.addValue(value, compare))
                 else -> this
             }
             is Empty -> Node(value, emptyTree(), emptyTree())
         }
     }

    // Hilfsfunktion zur Ausgabe
    fun processPreOrder(f: (value: A) -> Unit) {
        when(this) {
            is Node -> {f(this.value)
                left.processPreOrder(f)
                right.processPreOrder(f)}
            is Empty -> {}
        }
    }

    fun <B> map(transform: (A) -> B): Tree<B> {
         return when(this) {
            is Node -> {Node(transform(this.value), left.map(transform), right.map(transform))}
            else -> emptyTree()
         }
    }

    //Hilfsfunktion zum Testen
    fun contains(value: A, compare: (A, A) -> Int): Boolean {
        return when (this) {
            is Node -> when {
                compare(value, this.value) == 0 -> return true
                compare(value, this.value) == -1 -> left.contains(value, compare)
                compare(value, this.value) == 1 -> right.contains(value, compare)
                else -> false //unnötiger teil
            }
            is Empty -> return false
        }
    }
}

//Ergänzung des Defaultwerts Empty
private data class Node<A>(val value: A, val left: Tree<A> = emptyTree(), val right: Tree<A> = emptyTree()) : Tree<A>()

object Empty: Tree <Nothing>()

    // Hilfsfunktionen zum Erstellen von Bäumen
fun <A> emptyTree(): Tree<A> = Empty as Tree<A>

fun <A> treeNode(value: A, left: Tree<A> = emptyTree(), right: Tree<A> = emptyTree()): Tree<A> =
    Node(value, left, right)