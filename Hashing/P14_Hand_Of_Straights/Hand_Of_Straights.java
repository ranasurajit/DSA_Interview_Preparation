package Hashing.P14_Hand_Of_Straights;

import java.util.TreeMap;

public class Hand_Of_Straights {
    public static void main(String[] args) {
        Hand_Of_Straights solution = new Hand_Of_Straights();

        int[] hand = { 1, 2, 3, 6, 2, 3, 4, 7, 8 };
        int groupSize = 3;
        boolean canBeGrouped = solution.isNStraightHand(hand, groupSize);
        System.out.println(canBeGrouped);
    }

    /**
     * Approach : Using Hashing Approach (Using an Order Map / TreeMap)
     * 
     * TC: O(4 x N x log(N))
     * SC: O(N)
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            map.put(hand[i], map.getOrDefault(hand[i], 0) + 1); // TC: O(log(N))
        }
        while (!map.isEmpty()) { // TC: O(N)
            int minimum = map.firstKey();
            for (int i = minimum; i < minimum + groupSize; i++) { // TC: O(3)
                if (!map.containsKey(i)) {
                    return false;
                }
                // map has an entry of minimum
                int freq = map.get(i);
                if (freq == 1) {
                    map.remove(i);
                } else {
                    map.put(i, map.get(i) - 1); // TC: O(log(N))
                }
            }
        }
        return true;
    }
}
