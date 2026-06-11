
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

public class Cache<K> {
    LinkedHashMap<Integer, K> cacheSystem;
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
        if(this.cacheSystem.containsValue(key)){
            K value = this.cacheSystem.entrySet().iterator().next().getValue();
            this.cacheSystem.replace(0, value);
            return value;
        }else{
            return null;
        }
    }

    public K evict(K value){
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

    private void evictLast(){
        this.cacheSystem.remove(0);
    }

    private void reorderLRU(){
        LinkedHashMap<Integer, K> temp = new LinkedHashMap<>();
        AtomicInteger n = new AtomicInteger(this.cacheSystem.size());
        this.cacheSystem.forEach((k, v) -> {
            temp.put(n.decrementAndGet(), v);
        });
        this.cacheSystem = temp;
    }


    


    public void print(){
        System.out.println(this.cacheSystem);
    }


}
