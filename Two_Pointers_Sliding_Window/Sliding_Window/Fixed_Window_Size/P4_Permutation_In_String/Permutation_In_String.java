package Two_Pointers_Sliding_Window.Sliding_Window.Fixed_Window_Size.P4_Permutation_In_String;

public class Permutation_In_String {
    public static void main(String[] args) {
        Permutation_In_String solution = new Permutation_In_String();

        String s1 = "ab";
        String s2 = "eidbaooo";

        boolean isIncluded = solution.checkInclusion(s1, s2);
        System.out.println(isIncluded);
    }

    /**
     * Approach : Using Sliding Window (Fixed Length) Approach
     *
     * TC: O(N + K)
     * SC: O(1)
     */
    public boolean checkInclusion(String s1, String s2) {
        int k = s1.length();
        int n = s2.length();
        int[] freq = new int[26];
        for (int i = 0; i < k; i++) { // TC: O(K)
            freq[s1.charAt(i) - 'a']++;
        }
        int i = 0;
        int j = 0;
        while (j < n) { // TC: O(N)
            freq[s2.charAt(j) - 'a']--;
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                if (allCharsZero(freq)) { // TC: O(26) ~ O(1)
                    return true;
                }
                // remove computation from index 'i'
                freq[s2.charAt(i) - 'a']++;
                // slide to next valid window of size k
                i++;
                j++;
            }
        }
        return false;
    }

    /**
     * TC: O(26) ~ O(1)
     * SC: O(1)
     */
    private boolean allCharsZero(int[] freq) {
        for (int i = 0; i < 26; i++) { // TC: O(26)
            if (freq[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
