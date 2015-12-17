package de.martenl.interview.datastructures;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by Marten on 09.03.2015.
 */
public class LinkedList<T> implements Iterable<T>{

    private Node<T> root;

    public int length(){
        if(root == null){
            return 0;
        }
        return root.length(0);
    }

    public LinkedList<T> add(T ... elements){
        if(elements.length == 0){
            return this;
        }
        if(root == null){
            root = new Node<T>(elements[0],null,null);
            for(int i = 1;i<elements.length;i++){
                root.add(elements[i]);
            }
        } else{
            for(T element : elements){
                root.add(element);
            }
        }
        return this;
    }

    public static <T> LinkedList<T> createList(T ... elements){
        LinkedList<T> result = new LinkedList<T>();
        for(T element : elements){
            result.add(element);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedList)) return false;

        LinkedList that = (LinkedList) o;
        if(length() != that.length()){
            return false;
        }
        if (root != null ? !root.equals(that.root) : that.root != null){
            return false;
        }
        for(int i = 0;i<length();i++){
            if(!get(i).equals(that.get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return root != null ? root.hashCode() : 0;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public LinkedList<T> clear(){
        root.clear();
        root = null;
        return this;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(this);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for(T item : this){
            action.accept(item);
        }
    }

    public <R> LinkedList<R> map(Function<T,R> mapping){
      LinkedList<R> result = new LinkedList<R>();
        for(T item : this){
            result.add(mapping.apply(item));
        }
        return result;
    }

    public T reduce(BinaryOperator<T> func,T startElement){
        T result = startElement;
        for(T item : this){
            result = func.apply(result,item);
        }
        return result;
    }

    public LinkedList<T>[] partitionBy(Predicate<T> predicate){
        LinkedList<T>[] result = new LinkedList[]{new LinkedList<T>(),new LinkedList<T>()};
        for(T item : this){
            if(predicate.test(item)){
                result[0].add(item);
            }else{
                result[1].add(item);
            }
        }
        return result;
    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    public LinkedList<Integer> where(Predicate<T> predicate){
        LinkedList<Integer> result = new LinkedList<Integer>();
        int index = 0;
        for(T item : this){
            if(predicate.test(item)){
                result.add(index);
            }
            index++;
        }
        return result;
    }

    public LinkedList<T> removeWhere(Predicate<T> predicate){
      for(Integer i : where(predicate).reverse()){
          remove(i);
      }
      return this;
    }

    public LinkedList<T> reverse() {
        if(root != null){
            root = root.reverse();
        }
        return this;
    }

    public T get(int index){
        return root.get(index);
    }

    public T remove(int index){
      if(index < 0 || index>=length()){
          return null;
      }
      if(index == 0){
          T result = root.remove(index);
          root = root.getNext();
          return result;
      }
      return root.remove(index);
    }

    public LinkedList<T> add(T element, int index) {
        if(index == 0){
            root = new Node<T>(element,root,null);
        }else {
            root.add(element,index-1);
        }
        return this;
    }

    class Node<T>{
        private final T data;
        private Node<T> next;
        private Node<T> previous;

        Node(T data, Node<T> next, Node<T> previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)){
                return false;
            }

            Node node = (Node) o;

            if (!data.equals(node.data)){
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            return data.hashCode();
        }

        public int length(final int count){
            final int newCount = count+1;
            if(next == null){
                return newCount;
            }
            return next.length(newCount);
        }

        public boolean hasNext(){
            return next != null;
        }

        public Node<T> getNext(){
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<T> previous) {
            this.previous = previous;
        }

        public void add(T elemData){
            if(next == null){
                next = new Node<T>(elemData,null,this);
            }else{
                next.add(elemData);
            }
        }

        public void add(T elemData,int index){
            if(index == 0){
                next = new Node<T>(elemData,next,this);
                return;
            }
            if(next == null){
                add(elemData);
                return;
            }
            next.add(elemData,index-1);
        }

        public T remove(int index){
            if(index == 0){
                if(previous != null){
                    previous.setNext(next);
                }
                if(next != null){
                    next.setPrevious(previous);
                }
                return data;
            }
            return next.remove(index-1);
        }

        public T get(int index){
            if(index == 0){
                return data;
            }
            if(next == null){
                return null;
            }
            return next.get(index-1);
        }

        public Node<T> reverse() {
            Node<T> _previous = previous;
            Node<T> _next = next;
            previous = _next;
            next = _previous;
            if(_next == null){
                return this;
            }
            return _next.reverse();
        }

        public void clear() {
            previous = null;
            if(next != null){
                next.clear();
            }
            next = null;
        }
    }

    private static class LinkedListIterator<T> implements Iterator<T>{

        private int position;
        private final LinkedList<T> list;

        private LinkedListIterator(LinkedList<T> list) {
            this.list = list;
            this.position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < list.length();
        }

        @Override
        public T next() {
            if(hasNext()){
                T result = list.get(position);
                position++;
                return result;
            }
            return null;
        }
    }
}

