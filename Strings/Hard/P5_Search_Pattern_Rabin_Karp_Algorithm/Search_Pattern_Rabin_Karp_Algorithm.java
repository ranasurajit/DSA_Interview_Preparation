package Strings.Hard.P5_Search_Pattern_Rabin_Karp_Algorithm;

import java.util.ArrayList;

public class Search_Pattern_Rabin_Karp_Algorithm {

    private static final int MOD = (int) 1e9 + 7;

    public static void main(String[] args) {
        Search_Pattern_Rabin_Karp_Algorithm solution = new Search_Pattern_Rabin_Karp_Algorithm();

        String text1 = "geeksforgeeks", pattern1 = "geek";
        ArrayList<Integer> rabinKarpMatchIndex1 = solution.rabinKarp(text1, pattern1);
        System.out.println(rabinKarpMatchIndex1);

        String text2 = "aabaacaadaabaaba", pattern2 = "aaba";
        ArrayList<Integer> rabinKarpMatchIndex2 = solution.rabinKarp(text2, pattern2);
        System.out.println(rabinKarpMatchIndex2);
    }

    /**
     * Approach : Using Rabin-Karp Algorithm Approach
     * 
     * TC: O(N) + O(N) + O(M - N) + O(N) ~ O(M + N)
     * SC: O(N)
     * 
     * while the worst case time complexity is still O(M x N)
     * we need to mitigate worst time complexity by creating a
     * string Hash Function
     */
    public ArrayList<Integer> rabinKarp(String text, String pattern) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int m = text.length();
        int n = pattern.length();
        if (n > m) {
            return result;
        }
        /**
         * We need to create a HashFunction to convert the 'pattern' and
         * search the same Hash value in the similar length portion of
         * String 'text' and keep checking - we call this Rolling Hash
         */
        long base = 31L;
        /**
         * We need to calculate powers of base for ease of calculation
         */
        long[] powers = new long[n]; // SC: O(N)
        powers[0] = 1L;
        for (int i = 1; i < n; i++) { // TC: O(N)
            powers[i] = (base * powers[i - 1]) % MOD;
        }
        long pHash = 0L;
        long tHash = 0L;
        for (int i = 0; i < n; i++) { // TC: O(N)
            pHash = ((pHash * base) % MOD + (pattern.charAt(i) - 'a')) % MOD;
            tHash = ((tHash * base) % MOD + (text.charAt(i) - 'a')) % MOD;
        }
        for (int i = 0; i < m - n + 1; i++) { // TC: O(M - N)
            if (pHash == tHash && isMatched(text, pattern, i, n)) { // TC: O(N)
                result.add(i);
            }
            /**
             * we need to remove calculation from index 'i' from tHash
             * so that next tHash can be calculated with index 'i + n'
             */
            if (i + n < m) {
                long removed = (powers[n - 1] * (text.charAt(i) - 'a')) % MOD;
                tHash = (tHash - removed + MOD) % MOD;
                tHash = ((tHash * base) % MOD + (text.charAt(i + n) - 'a')) % MOD;
            }
        }
        return result;
    }

    /**
     * Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    private boolean isMatched(String text, String pattern, int start, int n) {
        int p = 0; // pointer at the start of String pattern
        int q = start; // pointer at the start of String text
        while (p < n) { // TC: O(N)
            if (pattern.charAt(p) != text.charAt(q)) {
                return false;
            }
            p++;
            q++;
        }
        return true;
    }
}
