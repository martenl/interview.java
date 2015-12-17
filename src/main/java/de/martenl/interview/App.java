package de.martenl.interview;

import de.martenl.interview.datastructures.BinarySearchTree;
import de.martenl.interview.datastructures.LinkedList;
import de.martenl.interview.datastructures.Stack;

import java.util.function.Consumer;
import java.util.function.Function;

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
        //printFolge2();
        //printFolge(x->Math.pow(-1,x)* (x.doubleValue()/Math.pow(2,x)));
        //printFolge(0,x->Math.pow(-1,x)/ ((x.doubleValue()*2.0)+1.0));
        //printFolge(1,x->Math.sqrt(x)*Math.pow(-0.99999,x));
        //printFolge(1,x->Math.pow(-1,x)/(x - (200000*(x%2))));
        //printFolge(1,x->abs(cos(x)) / x);

    }

    static class Printer implements Consumer<Integer>{

        @Override
        public void accept(Integer item) {
            System.out.println(item);
        }
    }

    static private void printFolge(){
        double sum = 0.0;
        for (int i = 1;i<100;i++){
            double number = Math.pow(-1,i)/(i - (200000*(i%2)));
            sum += number;
            //if(i  == Integer.MAX_VALUE-1){
                System.out.println(i);
                System.out.println("Folge: "+number);
                System.out.println("Reihe: "+sum);
            //}
        }
    }

    static private void printFolge2(){
        double sum = 0.0;
        double diff = 0.0;
        for (int i = 1;i<10000;i++){
            double number = Math.pow(-1.1,i)/Math.pow(i,4);
            diff = (sum + number) - sum;
            sum += number;
            if(i  % 100 == 0){
                System.out.println(i);
                System.out.println("Folge: "+number);
                System.out.println("Reihe: "+sum);
                System.out.println("Differenz Reihe: "+diff);
            }
        }
    }

    static private void printFolge3(){
        double sum = 0.0;
        double diff = 0.0;
        for (int i = 1;i<10000;i++){
            double number = Math.pow(-1.1,i)/Math.pow(i,4);
            diff = (sum + number) - sum;
            sum += number;
            if(i  % 100 == 0){
                System.out.println(i);
                System.out.println("Folge: "+number);
                System.out.println("Reihe: "+sum);
                System.out.println("Differenz Reihe: "+diff);
            }
        }
    }

    static private void printFolge(int start,Function<Integer,Double> func){
        double sum = 0.0;
        double diff;
        for (int i = start;i<Integer.MAX_VALUE;i++){
            double number = func.apply(i);
            diff = (sum + number) - sum;
            sum += number;
            if(i  % 1000000 == 0){
                System.out.println(i);
                System.out.println("Folge: "+number);
                System.out.println("Reihe: "+sum);
                System.out.println("Differenz Reihe: "+diff);
            }
        }
    }
}
