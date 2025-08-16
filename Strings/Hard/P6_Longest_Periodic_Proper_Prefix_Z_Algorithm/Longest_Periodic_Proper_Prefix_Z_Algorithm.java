package Strings.Hard.P6_Longest_Periodic_Proper_Prefix_Z_Algorithm;

public class Longest_Periodic_Proper_Prefix_Z_Algorithm {
    public static void main(String[] args) {
        Longest_Periodic_Proper_Prefix_Z_Algorithm solution = new Longest_Periodic_Proper_Prefix_Z_Algorithm();

        String s1 = "aaaaaa";
        int longestPrefix1 = solution.getLongestPrefixBruteForce(s1);
        System.out.println(longestPrefix1);

        String s2 = "abcab";
        int longestPrefix2 = solution.getLongestPrefixOptimal(s2);
        System.out.println(longestPrefix2);
    }

    /**
     * Approach II : Using Z-Algorithm Approach
     * 
     * TC: O(N) + O(N)
     * SC: O(N)
     * 
     * Accepted (1120 / 1120 testcases passed)
     */
    int getLongestPrefixOptimal(String s) {
        int n = s.length();
        int[] zArr = zFunction(s, n); // TC: O(N), SC: O(N)
        // now we will compare from end of zArr if its value matches with current index
        for (int i = n - 1; i > 0; i--) { // TC: O(N)
            if (zArr[i] == n - i) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Using Z-Algorithm Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private int[] zFunction(String s, int n) {
        int[] zArr = new int[n]; // SC: O(N)
        zArr[0] = 0;
        // here z[i] represents substring starting at index 'i' that is a prefix and
        // suffix
        int left = 0; // left pointer of z-window
        int right = 0; // right pointer of z-window
        for (int k = 1; k < n; k++) { // TC: O(N)
            if (k > right) {
                // resetting left and right for re-comparizon
                left = k;
                right = k;
                // keep comapring till substring starting at k matches
                while (right < n && s.charAt(right) == s.charAt(right - left)) {
                    right++;
                }
                zArr[k] = right - left;
                right--;
            } else {
                int k1 = k - left;
                // check if the index is within z-window
                if (zArr[k1] < right - k + 1) {
                    zArr[k] = zArr[k1];
                } else {
                    // we need to again re-compare
                    left = k;
                    while (right < n && s.charAt(right) == s.charAt(right - left)) {
                        right++;
                    }
                    zArr[k] = right - left;
                    right--;
                }
            }
        }
        return zArr;
    }

    /**
     * Approach I : Using Brute-Force (String Simulation) Approach
     * 
     * TC: O(N x N)
     * SC: O(N)
     * 
     * Time Limit Exceeded (1110 / 1120 testcases passed)
     */
    int getLongestPrefixBruteForce(String s) {
        int n = s.length();
        /**
         * Proper prefix can go upto s[0:n - 1]
         */
        for (int i = 1; i < n; i++) { // TC: O(N)
            String prefix = s.substring(0, n - i); // TC: O(N), SC: O(N) - reused
            while (prefix.length() < s.length()) {
                prefix += prefix;
                if (prefix.startsWith(s)) { // TC: O(N)
                    return n - i;
                }
            }
        }
        return -1;
    }
}
