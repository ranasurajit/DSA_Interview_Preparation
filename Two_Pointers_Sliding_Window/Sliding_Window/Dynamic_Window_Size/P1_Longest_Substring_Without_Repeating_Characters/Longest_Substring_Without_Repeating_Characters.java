package Two_Pointers_Sliding_Window.Sliding_Window.Dynamic_Window_Size.P1_Longest_Substring_Without_Repeating_Characters;

import java.util.HashMap;

public class Longest_Substring_Without_Repeating_Characters {
    public static void main(String[] args) {
        Longest_Substring_Without_Repeating_Characters solution = new Longest_Substring_Without_Repeating_Characters();

        String s = "abcabcbb";
        int maxLength = solution.lengthOfLongestSubstring(s);
        System.out.println(maxLength);
    }

    /**
     * Approach : Using Sliding Window (Variable Length) Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>(); // SC: O(N)
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int maxLength = 0;
        while (j < n) { // TC: O(N)
            map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
            while (j - i + 1 > map.size()) {
                // remove computation from ith index
                int freq = map.get(s.charAt(i));
                if (freq == 1) {
                    map.remove(s.charAt(i));
                } else {
                    map.put(s.charAt(i), freq - 1);
                }
                // shrink the window
                i++;
            }
            if (j - i + 1 == map.size()) {
                // we got the desired window size
                maxLength = Math.max(maxLength, j - i + 1);
            }
            j++;
        }
        return maxLength;
    }
}
