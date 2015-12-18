package de.martenl.interview;

import de.martenl.interview.datastructures.BinarySearchTree;
import de.martenl.interview.datastructures.LinkedList;
import de.martenl.interview.datastructures.Stack;

/**
 * Created by Marten on 14.03.2015.
 */
public class App {

    public static void main(String[] args){
        LinkedList<Integer> list = LinkedList.createList(1,2,3,2,4).reverse().removeWhere(e -> e == 2);
        list.forEach(System.out::println);

        LinkedList<Integer>[] lists = LinkedList.createList(1,2,3,2,4).partitionBy(x -> x % 2 == 0);
        System.out.println("even:");
        lists[0].forEach(System.out::println);
        System.out.println("odd:");
        lists[1].forEach(System.out::println);
        System.out.println(LinkedList.createList("1","2","3","2","4").map(x -> Integer.parseInt(x)).reduce((x,y)-> x+y,0));
        LinkedList<String> strings = list.map(x->"*** "+x.toString()+" ***");
        strings.forEach(System.out::println);
        Stack<Integer> stack = Stack.createStack(1,2,3,4);
        System.out.println(stack.pop());
        stack.push(0);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        BinarySearchTree<Integer> bst = BinarySearchTree.create(4,8,7,1,9,2,6,3,5);
        System.out.println(bst.max());
        System.out.println(bst.min());
    }
}
