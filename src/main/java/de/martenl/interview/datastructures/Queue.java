package de.martenl.interview.datastructures;

/**
 * Created by Marten on 24.04.2015.
 */
public class Queue<T> {

    private final LinkedList<T> data;

    public Queue(){
        data = new LinkedList<>();
    }

    private Queue(LinkedList<T> data) {
        this.data = data;
    }

    public static <T> Queue<T> createQueue(T ... elements){
        LinkedList<T> data = LinkedList.createList(elements);
        return new Queue<>(data);
    }

    public Queue<T> enqueue(T element){
        data.add(element);
        return this;
    }

    public T dequeue(){
        return data.remove(0);
    }

    public T peek(){
        return data.get(0);
    }
}
