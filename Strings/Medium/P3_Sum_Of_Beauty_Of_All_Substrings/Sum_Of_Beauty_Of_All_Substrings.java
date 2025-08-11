package Strings.Medium.P3_Sum_Of_Beauty_Of_All_Substrings;

public class Sum_Of_Beauty_Of_All_Substrings {
    public static void main(String[] args) {
        Sum_Of_Beauty_Of_All_Substrings solution = new Sum_Of_Beauty_Of_All_Substrings();

        String s1 = "aabcb";
        int beautySum1 = solution.beautySum(s1);
        System.out.println(beautySum1);

        String s2 = "aabcbaa";
        int beautySum2 = solution.beautySum(s2);
        System.out.println(beautySum2);
    }

    /**
     * Approach : Using Hashing Approach
     *
     * TC: O(N x N x 26) ~ O(N x N)
     * SC: O(26) ~ O(1)
     */
    public int beautySum(String s) {
        int n = s.length();
        int sum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            int[] freq = new int[26]; // SC: O(26)
            for (int j = i; j < n; j++) { // TC: O(N)
                freq[s.charAt(j) - 'a']++;
                int minFreq = Integer.MAX_VALUE;
                int maxFreq = Integer.MIN_VALUE;
                for (int k = 0; k < 26; k++) { // TC: O(26)
                    if (freq[k] > 0) {
                        minFreq = Math.min(minFreq, freq[k]);
                        maxFreq = Math.max(maxFreq, freq[k]);
                    }
                }
                sum += maxFreq - minFreq;
            }
        }
        return sum;
    }
}
