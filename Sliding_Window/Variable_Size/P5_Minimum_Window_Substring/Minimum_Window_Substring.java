package Sliding_Window.Variable_Size.P5_Minimum_Window_Substring;

import java.util.HashMap;
import java.util.Map;

public class Minimum_Window_Substring {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String minWindowStr = minWindow(s, t);
        System.out.println(minWindowStr);
    }

    /**
     * Took two pointers i and j = 0
     * 
     * Window size: (j - i + 1)
     * 
     * Used HashMap to store the frequency of characters in String t
     * Used a count variable to count the number of distinct characters needed
     * Used a start variable to capture the start index of minimum window Substring
     * Used a minLength variable to capture the length of minimum window Substring
     * 
     * when HashMap had s.charAt(j), we decremented the frequency from HashMap and
     * decremented count if frequency = 0
     * 
     * till count = 0, we started comparing the minLength with current window size
     * and captured it along with starting index 'start'
     * and also shrinked the size to minimize it by removing the calculations
     * contributed by s.charAt(i) and then incremented i
     * 
     * 
     * TC: O(M + N)
     * SC: O(N)
     *
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m < n) {
            // all characters of String 't' cannot be present in String 's'
            return "";
        }
        // storing the frequency of all characters in String 't' in HashMap
        Map<Character, Integer> hm = new HashMap<Character, Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            char ch = t.charAt(i);
            hm.put(ch, hm.getOrDefault(ch, 0) + 1);
        }
        // storing count of minimum elements needed in String 's'
        int count = hm.size();
        int i = 0; // start index of sliding window
        int j = 0; // end index of sliding window
        int start = 0; // to store the starting index of our minimum window Substring
        int minLength = Integer.MAX_VALUE; // length of minimum window Substring
        while (j < m) { // TC: O(M)
            char ch = s.charAt(j);
            if (hm.containsKey(ch)) {
                hm.put(ch, hm.getOrDefault(ch, 0) - 1);
                if (hm.get(ch) == 0) {
                    count--;
                }
            }
            while (count == 0) {
                int currentWindowSize = j - i + 1;
                if (currentWindowSize < minLength) {
                    // capturing the minimum sliding window size and start index of it
                    minLength = currentWindowSize;
                    start = i;
                }
                // shrinking the window size so as to capture minimum sliding window size
                char rch = s.charAt(i);
                // removing calculation of character at index i before incrementing 'i'
                if (hm.containsKey(rch)) {
                    hm.put(rch, hm.getOrDefault(rch, 0) + 1);
                    if (hm.get(rch) > 0) {
                        count++;
                    }
                }
                i++;
            }
            j++;
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
    }
}
