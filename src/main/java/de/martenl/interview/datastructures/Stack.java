package de.martenl.interview.datastructures;

import java.util.Optional;

/**
 * Created by Marten on 24.04.2015.
 */
public class Stack<T> {

    private final LinkedList<T> data;

    public Stack() {
        data = new LinkedList<>();
    }

    Stack(LinkedList<T> data) {
        this.data = data;
    }

    public static <T> Stack<T> createStack(T ... elements){
        LinkedList<T> data = LinkedList.createList(elements);
        return new Stack<>(data);
    }

    public Stack<T> push(T element){
        data.add(element,0);
        return this;
    }

    public T pop() throws StackEmptyException{
        if(data.isEmpty()){
            throw new StackEmptyException("There are no elements in the stack");
        }
        return data.remove(0);
    }

    public Optional<T> peek(){
        if(data.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(data.get(0));
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }


}
