package Strings.Basics.P4_Valid_Anagram;

public class Valid_Anagram {
    public static void main(String[] args) {
        Valid_Anagram solution = new Valid_Anagram();

        String s1 = "anagram", t1 = "nagaram";
        boolean isAnagram1 = solution.isAnagram(s1, t1);
        System.out.println(isAnagram1);

        String s2 = "rat", t2 = "car";
        boolean isAnagram2 = solution.isAnagram(s2, t2);
        System.out.println(isAnagram2);
    }

    /**
     * Approach : Using Hashing Approach
     *
     * TC: O(N) + O(26) ~ O(N)
     * SC: O(26) ~ O(1)
     */
    public boolean isAnagram(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m != n) {
            return false;
        }
        int[] freq = new int[26]; // SC: O(26)
        for (int i = 0; i < n; i++) { // TC: O(N)
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) { // TC: O(26)
            if (freq[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
