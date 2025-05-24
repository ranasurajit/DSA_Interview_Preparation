package Hashing.P2_Unique_Number_Of_Occurrences;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Unique_Number_Of_Occurrences {
    public static void main(String[] args) {
        Unique_Number_Of_Occurrences solution = new Unique_Number_Of_Occurrences();

        int[] arr1 = { 1, 2, 2, 1, 1, 3 };

        boolean isUnique1 = solution.uniqueOccurrencesApproachI(arr1);
        System.out.println(isUnique1);

        int[] arr2 = { 1, 2 };
        boolean isUnique2 = solution.uniqueOccurrencesApproachII(arr2);
        System.out.println(isUnique2);
    }

    /**
     * Approach II : Using Hashing Approach (Cleaner Approach)
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(2 x N) ~ O(N)
     */
    public boolean uniqueOccurrencesApproachII(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
        }
        Set<Integer> freqSet = new HashSet<Integer>(); // SC: O(N)
        for (Integer key : freqMap.keySet()) { // TC: O(N)
            freqSet.add(freqMap.get(key));
        }
        return freqSet.size() == freqMap.size();
    }

    /**
     * Approach I : Using Hashing Approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(2 x N) ~ O(N)
     */
    public boolean uniqueOccurrencesApproachI(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
        }
        Set<Integer> freqSet = new HashSet<Integer>(); // SC: O(N)
        for (Integer key : freqMap.keySet()) { // TC: O(N)
            int currentFreq = freqMap.get(key);
            if (freqSet.contains(currentFreq)) {
                return false;
            }
            freqSet.add(currentFreq);
        }
        return true;
    }
}
