package de.martenl.interview.datastructures;

import java.util.Optional;

/**
 * Created by Marten on 24.04.2015.
 */
public class BinarySearchTree<T extends Comparable> {

    private Optional<BSTNode<T>> root = Optional.empty();

    public BinarySearchTree<T> insert(T element){
        if(root.isPresent()){
            root.get().insert(element);
        }else{
            root = Optional.of(new BSTNode<T>(element));
        }
        return this;
    }

    public BinarySearchTree<T> delete(T element){
        return this;
    }

    public Optional<T> search(T element){
        return null;
    }

    public Optional<T> min(){
        if(root.isPresent()){
            return Optional.of(root.get().min());
        }
        return Optional.empty();
    }

    public Optional<T> max(){
        if(root.isPresent()){
            return Optional.of(root.get().max());
        }
        return Optional.empty();
    }

    public static <T extends Comparable> BinarySearchTree<T> create(T ... elements){
        BinarySearchTree<T> createdTree =  new BinarySearchTree<>();
        for(T element : elements){
            createdTree.insert(element);
        }
        return createdTree;
    }

    private static class BSTNode<T extends Comparable>{
        private T data;
        private Optional<BSTNode<T>> left;
        private Optional<BSTNode<T>> right;

        BSTNode(T data){
            this.data = data;
            left = Optional.empty();
            right = Optional.empty();
        }

        public T min(){
            if(left.isPresent()){
                return left.get().min();
            }
            return data;
        }

        public T max(){
            if(right.isPresent()){
                return right.get().max();
            }
            return data;
        }

        public void insert(T newData){
            final int comparison = data.compareTo(newData);
            if(comparison == 0){
                data = newData;
                return;
            }
            if(comparison > 0){
                insertLeft(newData);
                return;
            }
            insertRight(newData);
        }

        public T delete(T item){
            final int comparison = data.compareTo(item);
            if(comparison < 0){
                return deleteInChildNode(item, right);
            }else{
                return deleteInChildNode(item, left);
            }
        }

        private T deleteInChildNode(T item, Optional<BSTNode<T>> child) {
            if(!child.isPresent()){
                return null;
            }
            int comparison = data.compareTo(child.get().getData());
            if(comparison != 0){
                return child.get().delete(item);
            }
            return deleteChild(child);
        }

        private T deleteChild(Optional<BSTNode<T>> child) {
            
            return null;
        }

        public T getData() {
            return data;
        }

        public boolean isLeaf(){
            return !left.isPresent() && !right.isPresent();
        }

        private void insertRight(T newData) {
            if(right.isPresent()){
                right.get().insert(newData);
                return;
            }
            right = Optional.of(new BSTNode<T>(newData));
        }

        private void insertLeft(T newData) {
            if(left.isPresent()){
                left.get().insert(newData);
                return;
            }
            left = Optional.of(new BSTNode<T>(newData));
        }

        public Optional<T> search(T element){
            if(element.compareTo(data)==0){
              return Optional.of(data);
            }
            if(element.compareTo(data)>0){
                return searchRight(element);
            }
            return searchLeft(element);
        }

        private Optional<T> searchRight(T element) {
            if(right.isPresent()){
                return right.get().search(element);
            }
            return Optional.empty();
        }

        private Optional<T> searchLeft(T element) {
            if(left.isPresent()){
                return left.get().search(element);
            }
            return Optional.empty();
        }
    }
}
