package Two_Pointers_Sliding_Window.Sliding_Window.Dynamic_Window_Size.P5_Longest_Repeating_Character_Replacement;

public class Longest_Repeating_Character_Replacement {
    public static void main(String[] args) {
        Longest_Repeating_Character_Replacement solution = new Longest_Repeating_Character_Replacement();

        String s = "AABABBA";
        int k = 1;
        int longestSubstring = solution.characterReplacement(s, k);
        System.out.println(longestSubstring);
    }

    /**
     * Approach : Using Sliding Window (Variable Length) Approach
     *
     * TC: O(N)
     * SC: O(26) ~ O(1)
     */
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int[] freq = new int[26]; // SC: O(26) to store all A-Z frequencies
        int maxFreq = 0;
        int maxLength = 0;
        while (j < n) { // TC: O(N)
            char ch = s.charAt(j);
            freq[ch - 'A']++;
            maxFreq = Math.max(maxFreq, freq[ch - 'A']);
            // window size - maxFreq = number of replacements needed
            while ((j - i + 1) - maxFreq > k) {
                // remove computation from index 'i'
                freq[s.charAt(i) - 'A']--;
                // shrink the window
                i++;
            }
            if ((j - i + 1) - maxFreq <= k) {
                maxLength = Math.max(maxLength, j - i + 1);
            }
            j++;
        }
        return maxLength;
    }
}
