package Math_Combinatorics.Advanced_Math.P2_Prime_Factorization_Using_Sieve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prime_Factorization_Using_Sieve {
    private static int num;
    private static int[] primes;

    public static void main(String[] args) {
        Prime_Factorization_Using_Sieve solution = new Prime_Factorization_Using_Sieve();

        int N = 12246;
        List<Integer> primeFactors = solution.findPrimeFactors(N);
        System.out.println(primeFactors);
    }

    /**
     * Using Sieve Of Eratosthenes Approach
     * 
     * TC: O(N x log(log(N)))
     * SC: O(N)
     */
    private void sieve() {
        primes = new int[num + 1];
        Arrays.fill(primes, 1);
        primes[0] = 0;
        if (num > 1) {
            primes[1] = 0;
        }
        for (int i = 2; i * i <= num; i++) {
            for (int j = i * i; j <= num; j += i) {
                primes[j] = 0;
            }
        }
    }

    /**
     * Approach : Using Sieve Of Eratosthenes Approach :
     * 
     * TC: O(N x log(log(N))) + O(N) ~ O(N x log(log(N)))
     * SC: O(N)
     */
    private List<Integer> findPrimeFactors(int N) {
        num = N;
        sieve(); // TC: O(N x log(log(N))), SC: O(N)
        List<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= N; i++) { // TC: O(N)
            while (N > 0 && primes[i] == 1 && (N % i) == 0) {
                factors.add(i);
                N = N / i;
            }
        }
        return factors;
    }
}
