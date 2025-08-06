package Math_Combinatorics.Advanced_Math.P6_Fermat_Littles_Theoram_Modular_nCr;

public class Fermat_Littles_Theoram_Modular_nCr {
    private static final int MOD = (int) 1e9 + 3;

    public static void main(String[] args) {
        Fermat_Littles_Theoram_Modular_nCr solution = new Fermat_Littles_Theoram_Modular_nCr();

        long n = 5;
        long r = 2;
        int nCr = solution.nCr(n, r);
        System.out.println(nCr);
    }

    /**
     * Approach : Using Fermat Little's Theoram Approach
     * 
     * TC: O(N) + O(log(N) Base 2) ~ O(N)
     * SC: O(N) + O(log(N) Base 2) ~ O(N)
     */
    public int nCr(long n, long r) {
        if (r < 0 || r > n) {
            // invalid parameters
            return 0;
        }
        /**
         * nCr = factorial(n) / (factorial(r) * factorial(n - r))
         * so we need to pre-compute all factorial values for ease
         * and reduce time complexity
         */
        long[] fact = computeFactorial(n); // TC: O(N), SC: O(N)
        long a = fact[(int) n] % MOD;
        long b = ((fact[(int) r] % MOD) * (fact[(int) (n - r)] % MOD)) % MOD;
        /**
         * So we need to find (a / b) % MOD = (a * (b ^ -1)) % M
         * so to solve this we need to find P such that (a * P) % M
         * where P = Modular Inverse of b % M which by Fermat Little's
         * Theoram can be solved as
         * 
         * P = (b ^ (MOD - 2)) % MOD but (MOD - 2) is a very huge number
         * so to find the answer for (b ^ (MOD - 2)) we can use Binary
         * Exponentiation Approach
         */
        long p = fastPower(b, (long) (MOD - 2)) % MOD; // TC: O(log(MOD) Base 2), SC: O(log(MOD) Base 2)
        long result = a * p;
        return (int) (result % MOD);
    }

    /**
     * Using Binary Exponentiation Approach
     * 
     * TC: O(log(N) Base 2)
     * SC: O(log(N) Base 2)
     */
    private long fastPower(long x, long n) {
        if (n == 0L) {
            return 1;
        }
        if (n == 1L) {
            return x;
        }
        long ans = fastPower(x, n / 2);
        long result = (ans * ans) % MOD;
        if ((n & 1) != 0) {
            // odd power
            result = (x * result) % MOD;
        }
        return result % MOD;
    }

    /**
     * Using Math + Simulation Approach
     * 
     * TC: O(N)
     * SC: O(N)
     */
    private long[] computeFactorial(long n) {
        long[] fact = new long[(int) n + 1]; // SC: O(N)
        fact[0] = 1L;
        fact[1] = 1L;
        for (int i = 2; i <= n; i++) { // TC: O(N)
            fact[i] = ((long) i * fact[i - 1]) % MOD;
        }
        return fact;
    }
}
