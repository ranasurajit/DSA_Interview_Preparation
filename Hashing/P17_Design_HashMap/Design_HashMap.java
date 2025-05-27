package Hashing.P17_Design_HashMap;

import java.util.ArrayList;
import java.util.Arrays;

public class Design_HashMap {
    public static void main(String[] args) {
        String[] commands = {
                "MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"
        };
        int[][] arguments = { {}, { 1, 1 }, { 2, 2 }, { 1 }, { 3 }, { 2, 1 }, { 2 }, { 2 }, { 2 } };
        MyHashMap hashMap = null;
        ArrayList<Object> result = new ArrayList<Object>();

        for (int i = 0; i < commands.length; i++) {
            String command = commands[i];
            if (command.equals("MyHashMap")) {
                hashMap = new MyHashMap();
                result.add(null);
            } else if (command.equals("put")) {
                hashMap.put(arguments[i][0], arguments[i][1]);
                result.add(null);
            } else if (command.equals("get")) {
                result.add(hashMap.get(arguments[i][0]));
            } else if (command.equals("remove")) {
                hashMap.remove(arguments[i][0]);
                result.add(null);
            }
        }
        System.out.println(result);
    }

    /**
     * Approach : Using an Array Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    static class MyHashMap {

        int[] nums = new int[(int) 1e6 + 1];

        /**
         * TC: O(1)
         * SC: O(1e6 + 1) ~ O(1)
         */
        public MyHashMap() {
            Arrays.fill(nums, -1);
        }

        /**
         * TC: O(1)
         * SC: O(1)
         */
        public void put(int key, int value) {
            nums[key] = value;
        }

        /**
         * TC: O(1)
         * SC: O(1)
         */
        public int get(int key) {
            return nums[key];
        }

        /**
         * TC: O(1)
         * SC: O(1)
         */
        public void remove(int key) {
            nums[key] = -1;
        }
    }

    /**
     * Your MyHashMap object will be instantiated and called as such:
     * MyHashMap obj = new MyHashMap();
     * obj.put(key,value);
     * int param_2 = obj.get(key);
     * obj.remove(key);
     */
}
