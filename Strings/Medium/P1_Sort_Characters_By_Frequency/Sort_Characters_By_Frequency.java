package Strings.Medium.P1_Sort_Characters_By_Frequency;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Sort_Characters_By_Frequency {
    public static void main(String[] args) {
        Sort_Characters_By_Frequency solution = new Sort_Characters_By_Frequency();

        String s1 = "tree";
        String sortedStr1 = solution.frequencySort(s1);
        System.out.println(sortedStr1);

        String s2 = "cccaaa";
        String sortedStr2 = solution.frequencySort(s2);
        System.out.println(sortedStr2);

        String s3 = "Aabb";
        String sortedStr3 = solution.frequencySort(s3);
        System.out.println(sortedStr3);
    }

    /**
     * Approach : Using Hashing + PriorityQueue (Max-Heap) Approach
     *
     * TC: O(N) + O(N x log(N)) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(N) + O(N) ~ O(N)
     */
    public String frequencySort(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<Character, Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((p, q) -> {
            if (p.freq == q.freq) {
                return p.ch - q.ch;
            }
            return q.freq - p.freq;
        }); // SC: O(N)
        for (Character key : map.keySet()) { // TC: O(N)
            pq.offer(new Pair(key, map.get(key))); // TC: O(log(N))
        }
        StringBuilder sb = new StringBuilder(); // SC: O(N)
        while (!pq.isEmpty()) { // TC: O(N)
            Pair current = pq.poll(); // TC: O(log(N))
            for (int i = 0; i < current.freq; i++) {
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
