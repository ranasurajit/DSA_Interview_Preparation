package Recursion.P6_Kth_Symbol_In_Grammar;

public class Kth_Symbol_In_Grammar {
    private double[] power;

    public static void main(String[] args) {
        Kth_Symbol_In_Grammar solution = new Kth_Symbol_In_Grammar();

        int n = 4;
        int k = 7;
        int grammer = solution.kthGrammar(n, k);
        System.out.println(grammer);
    }

    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(N x log(N) + N) ~ O(N x log(N))
     * SC: O(2 x N) ~ O(N)
     */
    public int kthGrammar(int n, int k) {
        power = new double[n + 1]; // SC: O(N)
        for (int i = 0; i < n + 1; i++) { // TC: O(N)
            power[i] = fastPow(2.0, i); // TC: O(log(N))
        }
        return solveRecursion(n, k); // TC: O(N), SC: O(N)
    }

    /**
     * TC: O(N)
     * SC: O(N)
     */
    private int solveRecursion(int n, int k) {
        // Base Case
        if (n == 1 && k == 1) {
            return 0;
        }
        // Hypothesis
        // at a level n, size of k = 2 ^ (n - 1)
        int size = (int) power[n - 1];
        int mid = size / 2;
        // Induction
        if (k <= mid) {
            return solveRecursion(n - 1, k);
        } else {
            return solveRecursion(n - 1, k - mid) == 0 ? 1 : 0;
        }
    }

    /**
     * TC: O(log(N))
     * SC: O(log(N))
     */
    private double fastPow(double x, int n) {
        if (n == 0 || x == 1) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        double pow = fastPow(x, n / 2);
        double ans = pow * pow;
        if ((n & 1) == 1) {
            // odd exponent
            ans = ans * x;
        }
        return ans;
    }
}
