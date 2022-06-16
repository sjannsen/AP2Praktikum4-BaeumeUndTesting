package trees


sealed class Tree {  //sealed, Ableitungen dieser Klasse müssen in dieser Datei definiert sein, sealed impliziert abstract?
    abstract fun size(): Int //Anzahl der Knoten
    abstract fun sum(): Int //Summe der Daten aller Knoten
    abstract fun contains(value: Int): Boolean
    abstract fun addSorted(value: Int): Tree // Muss einen neuen Baum zurückgeben, da left und richt val sind


    fun processNode(f: (value: Int) -> Unit) {
        when (this) {
            is EmptyTree -> {}
            is DataNode -> f(this.data)
        }
    }

    abstract fun processPreOrder(f: (value: Int) -> Unit)
    abstract fun processPostOrder(f: (value: Int) -> Unit)
    abstract fun processInOrder(f: (value: Int) -> Unit)
}

object EmptyTree : Tree() {
    override fun size() = 0 //leerer Baum hat 0 Elemente
    override fun sum() = 0 // Baum ist leer, keine Summe
    override fun contains(value: Int) = false // leerer Baum enthält nichts
    override fun addSorted(value: Int) = DataNode(value)

    override fun processPreOrder(f: (value: Int) -> Unit) {}
    override fun processPostOrder(f: (value: Int) -> Unit) {}
    override fun processInOrder(f: (value: Int) -> Unit) {}
}

//EmptyTree als default
class DataNode(val data: Int, val left: Tree = EmptyTree, val right: Tree = EmptyTree) : Tree() {
    override fun size() = 1 + left.size() + right.size() //rekursiver Aufruf
    override fun sum() = data + left.sum() + right.sum()
    override fun contains(value: Int) =
        value == data || (value < data && left.contains(value)) || (value > data && right.contains(value))

    override fun addSorted(value: Int) =
        if (value < data) DataNode(data, left.addSorted(value), right) else DataNode(data, left, right.addSorted(value))

    override fun processPreOrder(f: (value: Int) -> Unit) {
        processNode(f)
        left.processPreOrder(f)
        right.processPreOrder(f)
    }

    override fun processPostOrder(f: (value: Int) -> Unit) {
        left.processPostOrder(f)
        right.processPostOrder(f)
        processNode(f)
    }

    override fun processInOrder(f: (value: Int) -> Unit) {
        left.processInOrder(f)
        processNode(f)
        right.processInOrder(f)
    }
}