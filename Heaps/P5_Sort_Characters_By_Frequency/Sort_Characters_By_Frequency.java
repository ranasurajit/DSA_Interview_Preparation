package Heaps.P5_Sort_Characters_By_Frequency;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Sort_Characters_By_Frequency {
    public static void main(String[] args) {
        Sort_Characters_By_Frequency solution = new Sort_Characters_By_Frequency();

        String s1 = "cccaaa";
        String sortedString1 = solution.frequencySort(s1);
        System.out.println(sortedString1);

        String s2 = "Aabb";
        String sortedString2 = solution.frequencySort(s2);
        System.out.println(sortedString2);
    }

    /**
     * Approach : Using Max Heaps (PriorityQueues) + Hashing Approach
     *
     * TC: O(N) + O(K x log(K)) + O(N) ~ O(N + K x log(K))
     * SC: O(K) + O(K) + O(N) ~ O(N + K)
     */
    public String frequencySort(String s) {
        int n = s.length();
        Map<Character, Integer> freqMap = new HashMap<Character, Integer>(); // SC: O(K)
        for (int i = 0; i < n; i++) { // TC: O(N)
            char ch = s.charAt(i);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        // using a Max-Heap to store the frequency of Characters in s
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((p, q) -> q.freq - p.freq); // SC: O(K)
        for (Character key : freqMap.keySet()) { // TC: O(K)
            pq.offer(new Pair(key, freqMap.get(key))); // TC: O(log(K))
        }
        StringBuilder sb = new StringBuilder(); // SC: O(N)
        while (!pq.isEmpty()) { // TC: O(K)
            Pair current = pq.poll();
            for (int i = 0; i < current.freq; i++) { // TC: O(N / K)
                sb.append(current.ch);
            }
        }
        return sb.toString();
    }

    class Pair {
        char ch;
        int freq;

        public Pair(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }
}
