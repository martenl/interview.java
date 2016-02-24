package de.martenl.interview.datastructures;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by Marten on 24.04.2015.
 */
public class Graph<T> {

    final LinkedList<T> vertices;
    final HashTable<T,LinkedList<Edge<T>>> edges;

    public Graph() {
        this.vertices = new LinkedList<>();
        this.edges = new HashTable<>();
    }

    public Graph<T> addVertex(T vertex){
        if(!vertices.contains(vertex)){
            vertices.add(vertex);
        }
        return this;
    }

    public Graph<T> deleteVertex(T element){
        vertices.removeWhere(vertex -> vertex.equals(element));
        deleteEdgesFrom(element);
        deleteEdgesTo(element);
        return this;
    }

    private void deleteEdgesFrom(T vertex){
        edges.remove(vertex);
    }

    private void deleteEdgesTo(T vertex){
        for(LinkedList<Edge<T>> edgeList : edges.values()){
            edgeList.removeWhere(edge -> edge.getTo().equals(vertex));
        }
    }

    public Graph<T> deleteEdge(T from, T to){
        if(edges.contains(from)){
            edges.get(from).removeWhere(edge -> edge.getTo().equals(to));
        }
        return this;
    }

    public Graph<T> addEdge(T from,T to,double weight){
        addEdgeInternal(from, to, weight, true);
        return this;
    }

    public Graph<T> addEdge(T from,T to){
        return addEdge(from,to,1.0);
    }

    public Graph<T> addUndirectedEdge(T from,T to,double weight){
        addEdgeInternal(from, to, weight, false);
        return this;
    }

    public Graph<T> addUndirectedEdge(T from,T to){
        return addUndirectedEdge(from,to,1.0);
    }

    public void print(){
        vertices.forEach(
                vertex -> {
                    System.out.println(vertex);
                    if(edges.contains(vertex)){
                        printEdges(edges.get(vertex));
                    }else{
                        System.out.println("\tNo edges :(");
                    }
                }
        );
    }

    private void printEdges(LinkedList<Edge<T>> edges){
        edges.forEach(
                edge -> System.out.println("\t"+edge.to+" ("+edge.weight+")")
        );
    }

    private void addEdgeInternal(T from, T to, double weight,boolean directed) {
        if(!vertices.contains(from)){
            vertices.add(from);
        }
        if(!vertices.contains(to)){
            vertices.add(to);
        }
        if(!edges.contains(from)){
            edges.put(from,new LinkedList<Edge<T>>());
        }
        edges.get(from).add(new Edge(from, to, weight));
        if(!directed){
            addEdgeInternal(to,from,weight,true);
        }
    }

    public Iterator<Edge<T>> edgeIterator(){
        return new EdgeIterator<T>(edges);
    }

    public class Edge<T> implements Comparable<Edge<T>>{

        final T from;
        final T to;
        final double weight;

        public Edge(T from, T to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public T getFrom() {
            return from;
        }

        public T getTo() {
            return to;
        }

        public double getWeight() {
            return weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o){
                return true;
            }
            if (!(o instanceof Edge)){
                return false;
            }
            Edge<?> edge = (Edge<?>) o;
            return from.equals(edge.from)  && to.equals(edge.to);

        }

        @Override
        public int hashCode() {
            int result = from.hashCode();
            result = 31 * result + to.hashCode();
            return result;
        }

        @Override
        public int compareTo(Edge<T> o) {
            if(o.getWeight()<weight){
                return 1;
            }
            if(weight<o.getWeight()){
                return -1;
            }
            return 0;
        }
    }

    public class EdgeIterator<T> implements Iterator<Edge<T>>{

        final LinkedList<Iterator<Edge<T>>> iterators;

        public EdgeIterator(HashTable<T,LinkedList<Edge<T>>> adjencyMap) {
            iterators = new LinkedList<>();
            for(T key : adjencyMap.keys()){
                iterators.add(adjencyMap.get(key).iterator());
            }
        }

        @Override
        public boolean hasNext() {
            if(!iterators.get(0).hasNext()){
                iterators.remove(0);
            }
            if(iterators.isEmpty()){
                return false;
            }
            return iterators.get(0).hasNext();
        }

        @Override
        public Edge<T> next() {
            if(iterators.isEmpty()){
                return null;
            }
            return iterators.get(0).next();
        }

        @Override
        public void forEachRemaining(Consumer<? super Edge<T>> action) {
            while(hasNext()){
                action.accept(next());
            }
        }
    }
}
