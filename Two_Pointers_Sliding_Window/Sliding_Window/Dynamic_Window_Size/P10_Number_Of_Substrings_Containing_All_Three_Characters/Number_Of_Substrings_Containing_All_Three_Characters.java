package Two_Pointers_Sliding_Window.Sliding_Window.Dynamic_Window_Size.P10_Number_Of_Substrings_Containing_All_Three_Characters;

import java.util.HashMap;
import java.util.Map;

public class Number_Of_Substrings_Containing_All_Three_Characters {
    public static void main(String[] args) {
        Number_Of_Substrings_Containing_All_Three_Characters solution = new Number_Of_Substrings_Containing_All_Three_Characters();

        String s = "abcabc";

        int countSubArraysApproachI = solution.numberOfSubstringsApproachI(s);
        System.out.println(countSubArraysApproachI);

        int countSubArraysApproachII = solution.numberOfSubstringsApproachII(s);
        System.out.println(countSubArraysApproachII);
    }

    /**
     * Approach II : Using Sliding Window (Variable Size) Approach (Cleaner
     * Approach)
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * @param s
     * @return
     */
    public int numberOfSubstringsApproachII(String s) {
        int n = s.length();
        int[] dict = new int[3]; // SC: O(3) ~ O(1)
        int i = 0;
        int j = 0;
        int count = 0;
        while (j < n) { // TC: O(N)
            dict[s.charAt(j) - 'a']++;
            while (dict[0] > 0 && dict[1] > 0 && dict[2] > 0) {
                // at index j, all sub-arrays from [i..j] to [i..j..n] will satisfy
                count += (n - j);
                // remove computation from index 'i'
                dict[s.charAt(i) - 'a']--;
                // shrink the window
                i++;
            }
            j++;
        }
        return count;
    }

    /**
     * Approach I : Using Sliding Window (Variable Size) Approach
     * 
     * TC: O(2 x N) ~ O(N)
     * SC: O(K)
     * 
     * @param s
     * @return
     */
    public int numberOfSubstringsApproachI(String s) {
        int n = s.length();
        return countSubstringsLessThanEqualsK(s, n, 3) - countSubstringsLessThanEqualsK(s, n, 2);
    }

    /**
     * Using Sliding Window (Variable Size) Approach
     * 
     * TC: O(N)
     * SC: O(K)
     * 
     * @param s
     * @param n
     * @param k
     * @return
     */
    private int countSubstringsLessThanEqualsK(String s, int n, int k) {
        int i = 0;
        int j = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>(); // SC: O(K)
        int count = 0;
        while (j < n) { // TC: O(N)
            char ch = s.charAt(j);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.size() > k) {
                // remove computation from index 'i'
                int freq = map.get(s.charAt(i));
                if (freq == 1) {
                    map.remove(s.charAt(i));
                } else {
                    map.put(s.charAt(i), freq - 1);
                }
                i++;
            }
            count += (j - i + 1);
            j++;
        }
        return count;
    }
}
