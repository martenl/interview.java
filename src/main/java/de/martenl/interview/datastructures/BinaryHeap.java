package de.martenl.interview.datastructures;

public class BinaryHeap<T extends Comparable> {

    public static int parent(int index){
        return index / 2;
    }

    public static int left(int index){
        return 2*index;
    }

    public static int right(int index){
        return left(index)+1;
    }


}
