package de.martenl.interview.datastructures;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marten on 19.04.2015.
 */
public class HashTable<K,V> {

    private final LinkedList<LinkedList<Item<K,V>>> buckets;

    public HashTable() {
        buckets = new LinkedList<>();
        for(int i = 0;i<13;i++){
            buckets.add(new LinkedList<>());
        }
    }

    public HashTable<K,V> put(K key, V value){
        Item<K,V> item = new Item<>(key,value);
        LinkedList<Item<K,V>> bucket = getBucket(key);
        bucket.removeWhere(e -> e.getKey().equals(key));
        bucket.add(item);
        return this;
    }

    public V get(K key){
        LinkedList<Item<K,V>> bucket = getBucket(key);
        for(Item<K,V> item : bucket){
          if(item.getKey().equals(key)){
            return item.getValue();
          }
        }
        return null;
    }

    public V remove(K key){
        V result = null;
        LinkedList<Item<K,V>> bucket = getBucket(key);
        for(Item<K,V> item : bucket){
            if(item.getKey().equals(key)){
                result = item.getValue();
            }
        }
        bucket.removeWhere(e -> e.getKey().equals(key));
        return result;
    }

    public Set<K> keys(){
        Set<K> result = new HashSet<>();
        buckets.forEach(
                bucket -> bucket.forEach(
                        item -> result.add(item.getKey())));
        return result;
    }

    public Set<V> values(){
        Set<V> result = new HashSet<>();
        for(LinkedList<Item<K,V>> bucket : buckets){
            for(Item<K,V> item : bucket){
                result.add(item.getValue());
            }
        }
        return result;
    }

    private LinkedList<Item<K,V>> getBucket(K key){
        int hash = key.hashCode();
        int bucketIndex = hash < 0 ? -hash % buckets.length() : hash % buckets.length();
        LinkedList<Item<K,V>> bucket = buckets.get(bucketIndex);
        return bucket;
    }

    public boolean contains(K key) {
        return keys().contains(key);
    }

    private static class Item<K,V>{
        private final K key;
        private final V value;

        private Item(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
