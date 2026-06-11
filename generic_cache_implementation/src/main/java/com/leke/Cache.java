package com.leke;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Cache<K> {
    private LinkedHashMap<Integer, K> cacheSystem;
    int cacheSize;
    
    public Cache(int size){
        this.cacheSystem = new LinkedHashMap<>();
        this.cacheSize = size;
    }

    //Need to fix key value with putting into a full LRU cache
    public void put(K key){
        this.cacheSystem.putFirst(this.cacheSystem.size(), key);
        if(this.cacheSystem.size() > cacheSize){
            System.out.println("Running eviction...");
            evictLast();
        }
    }

    public K get(K key){
        K value = null;
        if(this.cacheSystem.containsValue(key)){
            
            for(int i = 0; i < this.cacheSystem.size(); i++){
                K getValue = this.cacheSystem.get(i);
                if(getValue.equals(key)){
                    value = getValue;
                    this.cacheSystem.remove(i);
                    // break;
                }
            }
            reorderLRU();
            this.cacheSystem.putFirst(this.cacheSystem.size(), value);
            
        }
        return value;
    }

    private K evict(K value){
        for(Entry<Integer, K> e : this.cacheSystem.entrySet()){
            if(value.equals(e.getValue())){
                int key = e.getKey();
                K removedValue = this.cacheSystem.remove(key);
                reorderLRU();
                return removedValue;
         }
        }
        return null;
    }

    public K remove(K value){
        return evict(value);
    }

    private void evictLast(){
        this.cacheSystem.remove(0);
        reorderLRU();
    }

    private void reorderLRU(){
        LinkedHashMap<Integer, K> temp = new LinkedHashMap<>();
        AtomicInteger n = new AtomicInteger(this.cacheSystem.size());
        this.cacheSystem.forEach((k, v) -> {
            temp.put(n.decrementAndGet(), v);
        });
        this.cacheSystem = temp;
    }

    @Override
    public boolean equals(Object cache){
        if(this == cache) return true;
        if(cache == null || getClass() != cache.getClass()) return false;

        Cache<?> typeCache = (Cache<?>) cache;
        return Objects.equals(this.cacheSystem, typeCache.cacheSystem);
        
    }


    @Override
    public int hashCode() {
        return Objects.hash(this);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(int i = 0; i < this.cacheSystem.size(); i++){
            sb.append(i + "=" + this.cacheSystem.get(i));
            if((i+1) < this.cacheSystem.size()){
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public void print(){
        System.out.println(this.cacheSystem);
    }

    public LinkedHashMap<Integer, K> getCacheSystem(){
        return this.cacheSystem;
    }

}       
