package de.martenl.interview.datastructures;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by Marten on 25.04.2015.
 */
public class BinaryTree<T> {

    /*public static <T> BinaryTree<T> parse(String treeAsString){

    }

    private static <T> Node<T> parseInternal(String nodeAsString){
        Node<T> result = new Node(null);

        return result;
    }*/

    private final Node<T> root;

    public BinaryTree(Node<T> root) {
        this.root = root;
    }

    private static class Node<T> {

        private final T data;

        Optional<Node<T>> left;
        Optional<Node<T>> right;

        public Node(T data) {
            this.data = data;
            this.left = Optional.empty();
            this.right = Optional.empty();
        }

        public Node(T data,Node<T> left,Node<T> right){
            this.data = data;
            if(left != null){
                this.left = Optional.of(left);
            }else{
                this.left = Optional.empty();
            }
            if(right != null){
                this.right = Optional.of(right);
            }else{
                this.left = Optional.empty();
            }
        }


        public T getData(){
            return data;
        }

        public void setLeft(Node<T> node){
            left = Optional.of(node);
        }

        public void setRight(Node<T> node){
            right = Optional.of(node);
        }

        public Node<T> getLeft() {
            if(left.isPresent()){
                return left.get();
            }
            return null;
        }

        public Node<T> getRight() {
            if(right.isPresent()){
                return right.get();
            }
            return null;
        }

        public boolean isLeaf(){
            return !left.isPresent() && !right.isPresent();
        }

        public void inorderTraversal(Consumer<T> action){
            if(left.isPresent()){
                left.get().inorderTraversal(action);
            }
            action.accept(data);
            if(right.isPresent()){
                right.get().inorderTraversal(action);
            }
        }
    }
}
