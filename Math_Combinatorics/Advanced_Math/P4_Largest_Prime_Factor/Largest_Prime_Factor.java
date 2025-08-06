package Math_Combinatorics.Advanced_Math.P4_Largest_Prime_Factor;

import java.util.Arrays;

public class Largest_Prime_Factor {
    public static void main(String[] args) {
        Largest_Prime_Factor solution = new Largest_Prime_Factor();

        int n1 = 24;
        int maxPrimeFactor1 = solution.largestPrimeFactorUsingSieveOfEratosthenes(n1);
        System.out.println(maxPrimeFactor1);

        int n2 = 13195;
        int maxPrimeFactor2 = solution.largestPrimeFactorEfficientPrimeFactorization(n2);
        System.out.println(maxPrimeFactor2);
    }

    /**
     * Approach II : Using Efficient Prime Factorization Approach
     * 
     * TC: O(Sqrt(N))
     * SC: O(1)
     * 
     * Accepted (1111 / 1111 testcases passed)
     */
    private int largestPrimeFactorEfficientPrimeFactorization(int n) {
        int maxPrime = -1;
        while (n % 2 == 0) {
            n = n / 2;
            maxPrime = 2;
        }
        for (int i = 3; i * i <= n; i++) { // TC: O(Sqrt(N))
            while (n > 0 && (n % i) == 0) {
                n = n / i;
                maxPrime = i;
            }
        }
        if (n > 2) {
            // if still n > 2 then n is itself prime
            maxPrime = n;
        }
        return maxPrime;
    }

    /**
     * Approach I : Using Sieve Of Eratosthenes Approach
     * 
     * TC: O(N x log(log(N))) + O(N) ~ O(N x log(log(N)))
     * SC: O(N)
     * 
     * Time Limit Exceeded (1010 / 1111 testcases passed)
     */
    private int largestPrimeFactorUsingSieveOfEratosthenes(int n) {
        int[] primes = new int[n + 1]; // SC: O(N)
        Arrays.fill(primes, 1);
        primes[0] = 0;
        if (n > 1) {
            primes[1] = 0;
        }
        for (int i = 2; i * i <= n; i++) { // TC: O(N x log(log(N)))
            if (primes[i] == 1) {
                for (int j = i * i; j <= n; j += i) {
                    primes[j] = 0;
                }
            }
        }
        int maxPrimeFactor = 0;
        for (int i = 2; i <= n; i++) { // TC: O(N)
            while (n > 0 && primes[i] == 1 && n % i == 0) {
                maxPrimeFactor = Math.max(maxPrimeFactor, i);
                n = n / i;
            }
        }
        return maxPrimeFactor;
    }
}
