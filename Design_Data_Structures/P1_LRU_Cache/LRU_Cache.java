package Design_Data_Structures.P1_LRU_Cache;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRU_Cache {
    public static void main(String[] args) {
        String[] operations = { "LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get" };
        int[][] data = { { 2 }, { 1, 1 }, { 2, 2 }, { 1 }, { 3, 3 }, { 2 }, { 4, 4 }, { 1 }, { 3 }, { 4 } };

        List<Object> result = new ArrayList<Object>();
        List<Object> resultDeque = new ArrayList<Object>();
        LRUCache cache = null;
        LRUCacheUsingDeque cacheDeque = null;
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            if (operation.equals("LRUCache")) {
                cache = new LRUCache(data[i][0]);
                cacheDeque = new LRUCacheUsingDeque(data[i][0]);
                result.add(null);
                resultDeque.add(null);
            } else if (operation.equals("put")) {
                cache.put(data[i][0], data[i][1]);
                result.add(null);
                cacheDeque.put(data[i][0], data[i][1]);
                resultDeque.add(null);
            } else if (operation.equals("get")) {
                result.add(cache.get(data[i][0]));
                resultDeque.add(cache.get(data[i][0]));
            }
        }
        System.out.println(result);
        System.out.println(resultDeque);
    }

    /**
     * Using HashMap and Doubly-LinkedList
     *
     * TC: O(1)
     * SC: O(1)
     */
    static class LRUCache {

        class Node {
            int key;
            int val;
            Node prev;
            Node next;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
                prev = null;
                next = null;
            }
        }

        Map<Integer, Node> map;
        int capacity;
        Node head;
        Node tail;

        public LRUCache(int capacity) {
            this.map = new HashMap<Integer, Node>();
            this.capacity = capacity;
            // initializing DoublyLinkedList
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        private void insertAfterHead(Node node) {
            Node headNext = head.next;
            head.next = node;
            node.next = headNext;
            headNext.prev = node;
            node.prev = head;
            map.put(node.key, node);
        }

        private void deleteNode(Node node) {
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            map.remove(node.key);
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            // first delete the node from DoublyLinkedList
            Node node = map.get(key);
            deleteNode(node);
            // insert the node after head to make it recently used
            insertAfterHead(node);
            return node.val;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                // update the node value in DoublyLinkedList
                Node node = map.get(key);
                deleteNode(node);
            } else {
                // size exceeds capacity
                if (map.size() == capacity) {
                    // we need to delete the least recently used Node
                    deleteNode(tail.prev);
                }
            }
            Node newNode = new Node(key, value);
            insertAfterHead(newNode);
        }
    }

    /**
     * Using HashMap and ArrayDeque
     *
     * TC: O(1)
     * SC: O(1)
     */
    static class LRUCacheUsingDeque {
        private ArrayDeque<Integer> deque = null;
        private Map<Integer, Integer> map;
        private int n = 0;

        public LRUCacheUsingDeque(int capacity) {
            map = new HashMap<Integer, Integer>();
            deque = new ArrayDeque<Integer>();
            n = capacity;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                deque.remove(key);
                deque.addLast(key);
                return map.get(key);
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                deque.remove(key);
            } else {
                if (map.size() >= n) {
                    int leastUsedKey = deque.pollFirst();
                    map.remove(leastUsedKey);
                }
            }
            map.put(key, value);
            deque.addLast(key);
        }
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

}
