package Math_Combinatorics.Advanced_Math.P5_X_Pow_N;

public class X_Pow_N {
    public static void main(String[] args) {
        X_Pow_N solution = new X_Pow_N();

        double x1 = 2.00000;
        int n1 = 10;
        double pow1 = solution.myPow(x1, n1);
        System.out.println(pow1);

        double x2 = 2.10000;
        int n2 = 3;
        double pow2 = solution.myPow(x2, n2);
        System.out.println(pow2);

        double x3 = 2.00000;
        int n3 = -2;
        double pow3 = solution.myPow(x3, n3);
        System.out.println(pow3);
    }

    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(log(N) Base 2)
     * SC: O(log(N) Base 2)
     */
    public double myPow(double x, int n) {
        if (n < 0) {
            return 1 / fastPower(x, -1 * n);
        }
        return fastPower(x, n);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(log(N) Base 2)
     * SC: O(log(N) Base 2)
     */
    private double fastPower(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        double ans = fastPower(x, n / 2);
        double result = ans * ans;
        if ((n & 1) != 0) {
            result = result * x;
        }
        return result;
    }
}
