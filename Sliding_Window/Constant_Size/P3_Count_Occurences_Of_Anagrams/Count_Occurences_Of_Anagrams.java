package Sliding_Window.Constant_Size.P3_Count_Occurences_Of_Anagrams;

public class Count_Occurences_Of_Anagrams {
    public static void main(String[] args) {
        String txt = "forxxorfxdofr";
        String pat = "for";
        int count = search(pat, txt);
        System.out.println(count);
    }

    /**
     * k = size of window (String pat)
     * used a freqency array chars to store frequency of all characters in String
     * 'pat'
     * 
     * Took two pointers i and j = 0 and increment j till window size of 'k' is
     * reached
     * 
     * decrement frequency in 'chars' array for index at j
     * 
     * Window size: (j - i + 1)
     * 
     * when window size < k, then keep increasing j
     * when window size = k,
     * 1. check if frequency of all elements in 'chars' array = 0, then increment
     * count
     * 2. increase the frequency of ith index in 'chars' array to remove it from
     * next sliding windows
     * 
     * maintain this, by incrementing both i an j
     * 
     * TC: O(N + K)
     * SC: O(26) ~ O(1)
     * 
     * @param pat
     * @param txt
     * @return
     */
    private static int search(String pat, String txt) {
        int k = pat.length();
        int n = txt.length();
        int[] chars = new int[26];
        for (int i = 0; i < k; i++) { // TC: O(K)
            chars[pat.charAt(i) - 'a']++;
        }
        int count = 0;
        // window size is constant = m (pattern size);
        int i = 0; // pointer for start of sliding window
        int j = 0; // pointer for end of sliding window
        while (j < n) { // TC: O(N)
            chars[txt.charAt(j) - 'a']--;
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                if (isEmpty(chars)) { // TC: O(26)
                    count++;
                }
                /*
                 * removing the ith count from window by increasing
                 * the frequency of the char at index i
                 */
                chars[txt.charAt(i) - 'a']++;
                // move the sliding window
                i++;
                j++;
            }
        }
        return count;
    }

    /**
     * TC: O(26)
     * SC: O(1)
     * 
     * @param chars
     * @return
     */
    private static boolean isEmpty(int[] chars) {
        for (int i = 0; i < 26; i++) {
            if (chars[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
