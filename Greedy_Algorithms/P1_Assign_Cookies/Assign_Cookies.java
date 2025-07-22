package Greedy_Algorithms.P1_Assign_Cookies;

import java.util.Arrays;

public class Assign_Cookies {
    public static void main(String[] args) {
        Assign_Cookies solution = new Assign_Cookies();

        int[] g1 = { 1, 2, 3 };
        int[] s1 = { 1, 1 };
        int numChildrenContented1 = solution.findContentChildren(g1, s1);
        System.out.println(numChildrenContented1);

        int[] g2 = { 1, 2 };
        int[] s2 = { 1, 2, 3 };
        int numChildrenContented2 = solution.findContentChildren(g2, s2);
        System.out.println(numChildrenContented2);
    }

    /**
     * Approach : Using Greedy + Two Pointers Approach
     *
     * TC: O(M x log(M)) + O(N x log(N)) + O(M) ~ O(M x log(M) + O(N x log(N))
     * SC: O(1)
     */
    public int findContentChildren(int[] g, int[] s) {
        int m = g.length;
        int n = s.length;
        /**
         * We will sort the greed factor 'g' and cookie size 's' and check if
         * maximum value greed factor of child is satisfied with maximum value
         * of cookie size
         */
        Arrays.sort(g); // TC: O(M x log(M))
        Arrays.sort(s); // TC: O(N x log(N))
        // Using Two Pointers Approach
        int p = m - 1; // pointer at the end of array 'g'
        int q = n - 1; // pointer at the end of array 's'
        int contentedChildCount = 0;
        while (p >= 0 && q >= 0) { // TC: O(M)
            if (g[p] <= s[q]) {
                contentedChildCount++;
                q--;
            }
            p--;
        }
        return contentedChildCount;
    }
}
