package Greedy_Algorithms.P18_LRU_Cache;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRU_Cache {
    public static void main(String[] args) {
        String[] queries = { "LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get" };
        int[][] params = { { 2 }, { 1, 1 }, { 2, 2 }, { 1 }, { 3, 3 }, { 2 }, { 4, 4 }, { 1 }, { 3 }, { 4 } };
        List<Object> result = new ArrayList<Object>();
        LRUCache cache = null;
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            int[] queryParam = params[i];
            if (query.equals("LRUCache")) {
                cache = new LRUCache(queryParam[0]);
                result.add(null);
            } else if (query.equals("get")) {
                result.add(cache.get(params[i][0]));
            } else {
                cache.put(params[i][0], params[i][1]);
                result.add(null);
            }
        }
        System.out.println(result);
    }
}

/**
 * Approach : Using ArrayDeques + Hashing Approach
 *
 * TC: O(N)
 * SC: O(N) + O(N) ~ O(N)
 */
class LRUCache {
    int capacity;
    ArrayDeque<Integer> deque;
    Map<Integer, Integer> map;

    /**
     * TC: O(1)
     * SC: O(N) + O(N) ~ O(N)
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.deque = new ArrayDeque<Integer>();
        this.map = new HashMap<Integer, Integer>();
    }

    /**
     * TC: O(N)
     * SC: O(1)
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        int value = map.get(key);
        deque.remove(key); // TC: O(N)
        deque.addLast(key);
        return value;
    }

    /**
     * TC: O(N)
     * SC: O(1)
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            deque.remove(key); // TC: O(N)
            deque.addLast(key);
            map.put(key, value);
            return;
        }
        if (map.size() >= capacity) {
            map.remove(deque.pollFirst());
        }
        deque.addLast(key);
        map.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
