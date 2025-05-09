package Two_Pointers_Sliding_Window.Sliding_Window.Fixed_Window_Size.P2_Find_All_Anagrams_In_String;

import java.util.ArrayList;
import java.util.List;

public class Find_All_Anagrams_In_String {
    public static void main(String[] args) {
        Find_All_Anagrams_In_String solution = new Find_All_Anagrams_In_String();

        String s = "cbaebabacd";
        String p = "abc";

        List<Integer> anagramIndices = solution.findAnagrams(s, p);
        System.out.println(anagramIndices);
    }

    /**
     * Approach : Using Sliding Window (Fixed Length) Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();
        int k = p.length();
        int n = s.length();
        int[] chars = new int[26];
        for (int i = 0; i < k; i++) {
            chars[p.charAt(i) - 'a']++;
        }
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        while (j < n) { // TC: O(N)
            char ch = s.charAt(j);
            chars[ch - 'a']--;
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                if (isAnagram(chars)) { // TC: O(1)
                    result.add(i);
                }
                // remove computation from index 'i'
                chars[s.charAt(i) - 'a']++;
                // slide the window
                i++;
                j++;
            }
        }
        return result;
    }

    /**
     * TC: O(26) ~ O(1)
     * SC: O(1)
     */
    private boolean isAnagram(int[] chars) {
        for (int i = 0; i < 26; i++) {
            if (chars[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
