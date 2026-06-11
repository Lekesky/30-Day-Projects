import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.leke.Cache;

public class CacheTest {

    LinkedHashMap<Integer, Integer> cacheSystemTest;
    Cache<Integer> cache;

    @BeforeEach
    void init(){
        this.cacheSystemTest = new LinkedHashMap<>();
        this.cache = new Cache<>(5);
    }
    
    @Test
    public void testPut(){
        this.cache.put(5);
        this.cache.put(5);
        this.cacheSystemTest.put(0, 5);
        this.cacheSystemTest.put(1, 5);

        assertEquals(cacheSystemTest, cache.getCacheSystem());
    }

    @Test
    public void testGet(){
        this.cache.put(5);
        this.cache.put(10);
        this.cache.put(90);
        
        int getValue = this.cache.get(10);
        assertEquals(10, getValue);

        //Simulate LRU reordering within oringinal LinkedHashMap
        LinkedHashMap<Integer, Integer> temp = new LinkedHashMap<>();
        temp.putFirst(0, 5);
        temp.putFirst(1, 90);
        temp.putFirst(2, 10);
        this.cacheSystemTest = temp;

        assertEquals(cacheSystemTest, cache.getCacheSystem());
        

    }

    @Test
    public void testRemove(){
        this.cache.put(5);
        this.cache.put(10);
        this.cache.put(90);
        this.cache.put(400);

        this.cacheSystemTest.put(0, 5);
        this.cacheSystemTest.put(1, 10);
        this.cacheSystemTest.put(2, 90);
        this.cacheSystemTest.put(3, 400);

        int removedValueCache = this.cache.remove(90);
        int removedValueCacheSystemTest = this.cacheSystemTest.remove(2);

        //Simulate LRU reordering within original LinkedHashMap
        LinkedHashMap<Integer, Integer> temp = new LinkedHashMap<>();
        temp.put(0, 5);
        temp.put(1, 10);
        temp.put(2, 400);
        this.cacheSystemTest = temp;

        assertEquals(removedValueCacheSystemTest, removedValueCache);
        assertEquals(cacheSystemTest, cache.getCacheSystem());
    }
    
    @Test
    public void testLRUEviction(){
        this.cache.put(5);
        this.cache.put(10);
        this.cache.put(90);
        this.cache.put(400);
        this.cache.put(213);
        this.cache.put(2945);
        this.cache.put(1234);

        //Simulate LRU cache eviction results
        this.cacheSystemTest.putFirst(4, 1234);
        this.cacheSystemTest.putFirst(3, 2945);
        this.cacheSystemTest.putFirst(2, 213);
        this.cacheSystemTest.putFirst(1, 400);
        this.cacheSystemTest.putFirst(0, 90);



        assertEquals(cacheSystemTest, cache.getCacheSystem());
    }
}
