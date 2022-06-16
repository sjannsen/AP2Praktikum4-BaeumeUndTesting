package praktikum



fun main() {


    val n1 = treeNode(1)
    val n2 = treeNode(2)
    val n4 = treeNode(4)

    val n3 = treeNode(3, n2, n4)

    val n10 = treeNode(10)
    val n8 = treeNode(8, emptyTree(), n10)

    val root = treeNode(5, n3, n8)


    var root2 = treeNode(5, emptyTree(), emptyTree())
    root2 = root2.addValue(10) { a, b -> a.compareTo(b) } // in kotlin  pass such lambdas outside the parenthesis of the function call,
    root2 = root2.addValue(14, Int::compareTo) // Another way is to pass a method reference directly instead of a lambda:
    root2 = root2.addValue(4, Int::compareTo)
    root2 = root2.addValue(2, Int::compareTo)
    root2 = root2.addValue(11) {a, b -> a.compareTo(b)}
    root2 = root2.addValue(12, Int::compareTo)

    root2.processPreOrder { print("$it ") }
    /*
    * Note: passing the comparator function as argument to add is rather bad design (especially because this function is public),
    *  because the comparator can be different for each call adding elements to the tree. It should instead be a property of
    *  the tree itself, otherwise the tree could become inconsistent.
    * */

    /*   val n30 = DataNode(30)
       val n4 = DataNode(4)
       val n6 = DataNode(6)
       val n11 = DataNode(11, n30, n4)
       val root = DataNode(5, n11, n6)

       val anotherTree = DataNode(5, DataNode (11, DataNode(30, DataNode(4))), DataNode(6))

       println("Die Größe vom Baum root ist ${root.size()}")
       println("Die Summe des Baums root ist ${root.sum()}")
       println("Der Baum enthält die 4 ist ${root.contains(4)}")


       //Erzeugung eines sortierten Baums
       var root2: Tree = EmptyTree
       root2 = root2.addSorted(12)
       root2 = root2.addSorted(22)
       root2 = root2.addSorted(15)
       root2 = root2.addSorted(7)
       println()
       println("Erzeugung eines sortierten Baums")
       println("Die Größe des Baums root2: ${root2.size()}")
       println("Die Summe der Daten von root2: ${root2.sum()}")
       println("Der Baum enthält 15 ist ${root2.contains(15)}")

       println("ProcessInOrder()")
       root2.processInOrder({ println("${it}")})  //lamda Ausdrücke, Anonyme Funktionen, ohne Name
       println()

       println("ProcessPostOrder()")
       root2.processPostOrder({ println("${it}")})
       println()

       println("ProcessPreOrder")
       root2.processPreOrder({ println("${it}")})

       val liste1 = mutableListOf<Int>()
       root2.processInOrder { liste1.add(it) }
       println("Die Liste 1 enthält: ${liste1}")

       val gefilterteListe = liste1.filter{it > 10}
       println("Die gefilterte Liste mit it > 10: $gefilterteListe")

       var summe = 0
       root2.processInOrder { summe += it }
       println("Die Summe von root2 ist $summe")*/
}
