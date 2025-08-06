package Math_Combinatorics.Advanced_Math.P3_Count_Primes;

import java.util.Arrays;

public class Count_Primes {
    public static void main(String[] args) {
        Count_Primes solution = new Count_Primes();

        int n1 = 10;
        int countPrimes1 = solution.countPrimes(n1);
        System.out.println(countPrimes1);

        int n2 = 1;
        int countPrimes2 = solution.countPrimes(n2);
        System.out.println(countPrimes2);
    }

    /**
     * Approach : Using Sieve Of Eratosthenes Approach
     * 
     * TC: O(N x log(log(N))) + O(N) ~ O(N x log(log(N)))
     * SC: O(N)
     */
    public int countPrimes(int n) {
        if (n < 2) {
            return 0;
        }
        int[] primes = new int[n + 1]; // SC: O(N)
        Arrays.fill(primes, 1);
        primes[0] = 0;
        primes[1] = 0;
        for (int i = 2; i * i <= n; i++) { // TC: O(N x log(log(N)))
            if (primes[i] == 1) {
                for (int j = i * i; j <= n; j += i) {
                    primes[j] = 0;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (primes[i] == 1) {
                count++;
            }
        }
        return count;
    }
}
