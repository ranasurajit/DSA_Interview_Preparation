package Hashing.P12_Minimum_Number_Of_Pushes_To_Type_Word_I;

import java.util.HashMap;
import java.util.Map;

public class Minimum_Number_Of_Pushes_To_Type_Word_I {
    public static void main(String[] args) {
        Minimum_Number_Of_Pushes_To_Type_Word_I solution = new Minimum_Number_Of_Pushes_To_Type_Word_I();

        String word1 = "xycdefghij";
        int countPushes1 = solution.minimumPushesApproachI(word1);
        System.out.println(countPushes1);

        String word2 = "abcde";
        int countPushes2 = solution.minimumPushes(word2);
        System.out.println(countPushes2);
    }

    /**
     * Approach II : Using Hashing Approach (Cleaner Approach)
     * 
     * TC: O(N)
     * SC: O(26) ~ O(1)
     */
    public int minimumPushes(String word) {
        int[] freq = new int[26]; // SC: O(26)
        for (char ch : word.toCharArray()) { // TC: O(N)
            freq[ch - 'a']++;
        }
        int pushes = 0;
        int count = 0;
        for (int i = 0; i < 26; i++) { // TC: O(N)
            if (freq[i] > 0) {
                // we have keys 2-9 i.e. 8 keys to re-map the Characters
                pushes += (count / 8) + 1;
                count++;
            }
        }
        return pushes;
    }

    /**
     * Approach I : Using Hashing Approach
     * 
     * TC: O(2 x N) ~ O(N)
     * SC: O(N)
     */
    public int minimumPushesApproachI(String word) {
        int n = word.length();
        // we will store { Characters, freq } in HashMap
        Map<Character, Integer> map = new HashMap<Character, Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            char ch = word.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int size = map.size();
        int pushes = 0;
        int places = 8; // we have keys 2-9 i.e. 8 keys to re-map the Characters
        int tap = 1;
        while (size > 0) { // TC: O(N)
            int slab = Math.min(places, size);
            pushes += tap * slab;
            size = size - slab;
            tap++;
        }
        return pushes;
    }
}
