package policy.lru;

import java.util.LinkedHashMap;
import java.util.Map;
public class LRUCacheDemo<K,V> extends LinkedHashMap<K, V> {

        private int capacity;//缓存坑位

        public LRUCacheDemo(int capacity) {
                //排序模式：插入顺序为false,访问顺序为true
                super(capacity,0.75F,false);
                this.capacity = capacity;
        }


        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                //当有新的映射插入时，会删除排序第一的元素
                return super.size() > capacity;
        }

        public static void main(String[] args) {
                LRUCacheDemo lruCacheDemo = new LRUCacheDemo(3);

                lruCacheDemo.put(1,"a");
                lruCacheDemo.put(2,"b");
                lruCacheDemo.put(3,"c");
                System.out.println(lruCacheDemo.keySet());

                lruCacheDemo.put(4,"d");
                System.out.println(lruCacheDemo.keySet());

                lruCacheDemo.put(3,"c");
                System.out.println(lruCacheDemo.keySet());
                lruCacheDemo.put(3,"c");
                System.out.println(lruCacheDemo.keySet());
                lruCacheDemo.put(3,"c");
                System.out.println(lruCacheDemo.keySet());
                lruCacheDemo.put(5,"x");
                System.out.println(lruCacheDemo.keySet());
        }
}

/**
 * true
 * [1, 2, 3]
 * [2, 3, 4]
 * [2, 4, 3]
 * [2, 4, 3]
 * [2, 4, 3]
 * [4, 3, 5]
 * */

/**
 [1, 2, 3]
 [2, 3, 4]
 [2, 3, 4]
 [2, 3, 4]
 [2, 3, 4]
 [3, 4, 5]
 */
