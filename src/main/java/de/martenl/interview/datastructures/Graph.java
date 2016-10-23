package de.martenl.interview.datastructures;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by Marten on 24.04.2015.
 */
public class Graph<T> {

    private final LinkedList<T> vertices;
    private final HashTable<T,LinkedList<Edge<T>>> edges;

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

    public Graph<T> dfs(T startVertex) throws StackEmptyException {
        Graph<T> dfsGraph = new Graph<>();
        Stack<T> unseenVertices = Stack.createStack(startVertex);
        LinkedList<T> seenVertices = LinkedList.createList(startVertex);
        while(!unseenVertices.isEmpty()){
            T nextVertex = unseenVertices.pop();
            for(Edge<T> edge : edges.get(nextVertex)){
                if(!seenVertices.contains(edge.to)){
                    dfsGraph.addEdge(edge.getFrom(),edge.getTo(),edge.getWeight());
                }
                unseenVertices.push(edge.getTo());
            }
        }
        return dfsGraph;
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

    public Graph<T> prim(){
        Graph<T> mst = new Graph<>();
        LinkedList<Edge<T>> sortedEdges = ((EdgeIterator)edgeIterator()).toList()
                .selectionSort((x,y)->((Edge) x).getWeight()<((Edge)y).getWeight());
        for(Edge<T> edge : sortedEdges){

        }
        return mst;
    }

    public Iterator<Edge<T>> edgeIterator(){
        return new EdgeIterator<T>(edges);
    }

    public class Edge<S> implements Comparable<Edge<S>>{

        private final S from;
        private final S to;
        private final double weight;

        public Edge(S from, S to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public S getFrom() {
            return from;
        }

        public S getTo() {
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
        public int compareTo(Edge<S> o) {
            if(o.getWeight()<weight){
                return 1;
            }
            if(weight<o.getWeight()){
                return -1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

    public class EdgeIterator<T> implements Iterator<Edge<T>>{

        private final LinkedList<Iterator<Edge<T>>> iterators;

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

        public LinkedList<Edge<T>> toList(){
            LinkedList<Edge<T>> edges = new LinkedList<>();
            forEachRemaining(edge -> edges.add(edge));
            return edges;
        }
    }
}
