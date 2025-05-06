package Recursion.P1_Implement_Pow;

public class Implement_Pow {
    public static void main(String[] args) {
        Implement_Pow solution = new Implement_Pow();

        double x = 2.00000;
        int n = -2;

        double pow = solution.myPow(x, n);
        System.out.println(pow);
    }

    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(log(N))
     * SC: O(log(N))
     */
    public double myPow(double x, int n) {
        if (n < 0) {
            return 1 / fastPow(x, -1 * n);
        }
        return fastPow(x, n);
    }

    /**
     * TC: O(log(N))
     * SC: O(log(N))
     */
    private double fastPow(double x, int n) {
        // Base Case
        if (n == 0 || x == 1) {
            return 1.0;
        }
        if (n == 1) {
            return x;
        }
        // Hypothesis
        double pow = fastPow(x, n / 2);
        // Induction
        double ans = pow * pow;
        if ((n & 1) == 1) {
            // odd power
            ans = x * ans;
        }
        return ans;
    }
}
