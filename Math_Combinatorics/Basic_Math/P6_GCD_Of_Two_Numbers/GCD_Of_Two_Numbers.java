package Math_Combinatorics.Basic_Math.P6_GCD_Of_Two_Numbers;

public class GCD_Of_Two_Numbers {
    public static void main(String[] args) {
        GCD_Of_Two_Numbers solution = new GCD_Of_Two_Numbers();

        int a1 = 20;
        int b1 = 28;
        int gcd1 = solution.gcdBruteForce(a1, b1);
        System.out.println(gcd1);

        int a2 = 60;
        int b2 = 36;
        int gcd2 = solution.gcdEulersEuclidean(a2, b2);
        System.out.println(gcd2);
    }

    /**
     * Approach II : Using Euler's Euclidean GCD Algorithm Approach
     * 
     * TC: O(log(Min(a, b)))
     * SC: O(log(Min(a, b)))
     * 
     * Accepted (1115 /1115 testcases passed)
     */
    public int gcdEulersEuclidean(int a, int b) {
        if (b > a) {
            return gcdEulersEuclidean(b, a);
        }
        // ensure b < a
        if (b == 0) {
            return a;
        }
        return gcdEulersEuclidean(b, a % b);
    }

    /**
     * Approach I : Using Math Approach
     * 
     * TC: O(Min(a, b))
     * SC: O(1)
     * 
     * Time Limit Exceeded (1010 /1115 testcases passed)
     */
    public int gcdBruteForce(int a, int b) {
        int n = Math.min(a, b);
        for (int i = n; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                return i;
            }
        }
        return 1;
    }
}
