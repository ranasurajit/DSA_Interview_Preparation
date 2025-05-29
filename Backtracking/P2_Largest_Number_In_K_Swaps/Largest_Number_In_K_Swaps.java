package Backtracking.P2_Largest_Number_In_K_Swaps;

public class Largest_Number_In_K_Swaps {
    public static void main(String[] args) {
        Largest_Number_In_K_Swaps solution = new Largest_Number_In_K_Swaps();

        String s1 = "1234567";
        int k1 = 4;
        String largestNumber1 = solution.findMaximumNum(s1, k1);
        System.out.println(largestNumber1);

        String s2 = "1034";
        int k2 = 2;
        String largestNumber2 = solution.findMaximumNum(s2, k2);
        System.out.println(largestNumber2);
    }

    /**
     * Approach : Recursion + Backtracking Approach
     * 
     * Number of Nodes = N x (N - 1) x (N - 2) x ... x (N - K + 1)
     * Work done = N ^ 2
     * 
     * TC: O(N ^ 2 x (N! / (N - K)!))
     * SC: O(2 x N + K) ~ O(N + K)
     */
    public String findMaximumNum(String s, int k) {
        int n = s.length();
        String[] maxValue = { s };
        // TC: O(N ^ 2 x (N! / (N - K)!)), SC: O(2 x N + K)
        solveBacktrack(0, n, s.toCharArray(), k, maxValue);
        return maxValue[0];
    }

    /**
     * Recursion + Backtracking Approach
     * 
     * Number of Nodes = N x (N - 1) x (N - 2) x ... x (N - K + 1)
     * Work done = N ^ 2
     * 
     * TC: O(N ^ 2 x (N! / (N - K)!))
     * SC: O(N + K)
     */
    private void solveBacktrack(int index, int n, char[] chars, int k, String[] maxValue) {
        // Backtrack
        if (k == 0 || index == n) {
            return;
        }
        // Recursion Calls
        for (int i = index + 1; i < n; i++) { // TC: O(N)
            int max = -1;
            int idx = -1;
            for (int j = i; j < n; j++) { // TC: O(N)
                if (max < chars[j] - '0') {
                    max = chars[j] - '0';
                    idx = j;
                }
            }
            if ((chars[index] - '0') < (chars[idx] - '0')) {
                // swap the numbers at indices index with idx
                swap(chars, index, idx);
                // store the maxValue if found
                String current = String.valueOf(chars); // SC: O(N)
                if (current.compareTo(maxValue[0]) > 0) { // TC: O(N)
                    maxValue[0] = current;
                }
                // explore
                solveBacktrack(index + 1, n, chars, k - 1, maxValue);
                // backtrack
                swap(chars, index, idx);
            } else {
                solveBacktrack(index + 1, n, chars, k, maxValue);
            }
        }
    }

    /**
     * Using Swapping Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private void swap(char[] chars, int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }
}
