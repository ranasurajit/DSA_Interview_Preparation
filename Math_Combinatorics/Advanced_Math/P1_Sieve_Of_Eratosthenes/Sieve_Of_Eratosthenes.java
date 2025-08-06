package Math_Combinatorics.Advanced_Math.P1_Sieve_Of_Eratosthenes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sieve_Of_Eratosthenes {
    public static void main(String[] args) {
        Sieve_Of_Eratosthenes solution = new Sieve_Of_Eratosthenes();

        int n1 = 10;
        int[] primes1 = solution.sieve(n1);
        System.out.println(Arrays.toString(primes1));

        int n2 = 35;
        int[] primes2 = solution.sieve(n2);
        System.out.println(Arrays.toString(primes2));
    }

    /**
     * Approach : Using Sieve Of Eratosthenes Approach
     * 
     * TC: O(N x log(log(N))) + O(N) + O(N x log(log(N))) ~ O(N x log(log(N)))
     * SC: O(N) + O(N) ~ O(N)
     */
    public int[] sieve(int n) {
        int[] sieveArr = new int[n + 1]; // SC: O(N)
        precomputePrimes(sieveArr, n); // TC: O(N x log(log(N)))
        List<Integer> primeList = new ArrayList<Integer>(); // SC: O(N)
        for (int i = 2; i <= n; i++) { // TC: O(N)
            if (sieveArr[i] == 1) {
                primeList.add(i);
            }
        }
        int size = primeList.size();
        int[] primes = new int[size];
        int index = 0;
        while (index < size) { // TC: O(N x log(log(N)))
            primes[index] = primeList.get(index++);
        }
        return primes;
    }

    /**
     * Using Sieve Of Eratosthenes Approach
     * 
     * TC: O(N x log(log(N)))
     * SC: O(1)
     */
    private void precomputePrimes(int[] sieve, int n) {
        Arrays.fill(sieve, 1);
        // 0 and 1 are non-primes
        sieve[0] = 0;
        sieve[1] = 0;
        for (int i = 2; i * i <= n; i++) {
            for (int j = i * i; j <= n; j += i) {
                sieve[j] = 0;
            }
        }
    }
}
