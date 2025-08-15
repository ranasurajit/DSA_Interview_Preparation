package Strings.Hard.P4_Longest_Happy_Prefix;

public class Longest_Happy_Prefix {
    public static void main(String[] args) {
        Longest_Happy_Prefix solution = new Longest_Happy_Prefix();

        String s1 = "level";
        String longestPrefixSuffix1 = solution.longestPrefix(s1);
        System.out.println(longestPrefixSuffix1);

        String s2 = "ababab";
        String longestPrefixSuffix2 = solution.longestPrefix(s2);
        System.out.println(longestPrefixSuffix2);
    }

    /**
     * Approach : Using Longest Prefix Suffix Approach
     *
     * TC: O(N)
     * SC: O(N)
     */
    public String longestPrefix(String s) {
        int n = s.length();
        int[] lps = new int[n]; // SC: O(N)
        int len = 0;
        int i = 1;
        while (i < n) {
            if (s.charAt(len) == s.charAt(i)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                // fallback len to lps[len - 1]
                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return s.substring(0, lps[n - 1]);
    }
}
