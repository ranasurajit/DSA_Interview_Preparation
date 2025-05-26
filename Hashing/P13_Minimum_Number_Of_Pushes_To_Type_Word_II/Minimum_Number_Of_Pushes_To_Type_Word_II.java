package Hashing.P13_Minimum_Number_Of_Pushes_To_Type_Word_II;

import java.util.Arrays;

public class Minimum_Number_Of_Pushes_To_Type_Word_II {
    public static void main(String[] args) {
        Minimum_Number_Of_Pushes_To_Type_Word_II solution = new Minimum_Number_Of_Pushes_To_Type_Word_II();

        String word1 = "xyzxyzxyzxyz";
        int countPushes1 = solution.minimumPushes(word1);
        System.out.println(countPushes1);

        String word2 = "aabbccddeeffgghhiiiiii";
        int countPushes2 = solution.minimumPushes(word2);
        System.out.println(countPushes2);
    }

    /**
     * Approach : Using Hashing Approach (Cleaner Approach)
     * 
     * TC: O(N)
     * SC: O(26) ~ O(1)
     */
    public int minimumPushes(String word) {
        int[] freq = new int[26]; // SC: O(26)
        for (char ch : word.toCharArray()) { // TC: O(N)
            freq[ch - 'a']++;
        }
        /**
         * sorting the 'freq' so that we can assign and cover characters
         * having maximum frequency first to have actuated by minimum
         * pushes (starting from 1 push)
         */
        Arrays.sort(freq); // TC: O(26 x log(26)) ~ O(1)
        int count = 0;
        int pushes = 0;
        for (int i = 25; i >= 0; i--) { // TC: O(26)
            if (freq[i] > 0) {
                int occurence = freq[i];
                // we have keys 2-9 i.e. 8 keys to re-map the Characters
                // we use occurence as word may contain a character multiple times
                pushes += occurence * ((count / 8) + 1);
                count++;
            }
        }
        return pushes;
    }
}
