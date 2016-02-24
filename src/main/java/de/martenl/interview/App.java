package de.martenl.interview;

import de.martenl.interview.datastructures.BinarySearchTree;
import de.martenl.interview.datastructures.Graph;
import de.martenl.interview.datastructures.LinkedList;

import java.util.Iterator;

/**
 * Created by Marten on 14.03.2015.
 */
public class App {

    public static void main(String[] args){
        LinkedList<Integer> list = LinkedList.createList(1,2,3,2,4).reverse().removeWhere(e -> e == 2);
        list.forEach(System.out::println);
        System.out.println(list.contains(1));
        System.out.println(list.contains(2));
        LinkedList<Integer>[] lists = LinkedList.createList(1,2,3,2,4).partitionBy(x -> x % 2 == 0);
        System.out.println("even:");
        lists[0].forEach(System.out::println);
        System.out.println("odd:");
        lists[1].forEach(System.out::println);
        System.out.println(LinkedList.createList("1", "2", "3", "2", "4").map(x -> Integer.parseInt(x)).reduce((x, y) -> x + y, 0));
        LinkedList<String> strings = list.map(x -> "*** " + x.toString() + " ***");
        strings.forEach(System.out::println);

        BinarySearchTree<Integer> bst = BinarySearchTree.create(4,8,7,-1,9,2,6,3,5);
        System.out.println(bst.max());
        System.out.println(bst.min());

        System.out.println("Graphs, how do they work?");
        Graph<String> myGraph = new Graph<>();
        myGraph.addEdge("hello", "world", 3.14)
                .addEdge("hello","mars",2.7)
                .addEdge("world", "!", 1.0)
                .addUndirectedEdge("mars", "mars");
        myGraph.print();
        Iterator<Graph<String>.Edge<String>> edgeIter = myGraph.edgeIterator();
        edgeIter.forEachRemaining(x -> System.out.println(x.getFrom()+" -> "+x.getTo()+" ("+x.getWeight()+")"));
    }
}
