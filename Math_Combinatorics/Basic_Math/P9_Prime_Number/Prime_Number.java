package Math_Combinatorics.Basic_Math.P9_Prime_Number;

public class Prime_Number {
    public static void main(String[] args) {
        Prime_Number solution = new Prime_Number();

        int n1 = 7;
        boolean isPrime1 = solution.isPrime(n1);
        System.out.println(isPrime1);

        int n2 = 25;
        boolean isPrime2 = solution.isPrime(n2);
        System.out.println(isPrime2);
    }

    /**
     * Approach : Using Math + Simulation Approach
     * 
     * TC: O(Sqrt(N))
     * SC: O(1)
     */
    private boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        int countDivs = 0;
        for (int i = 2; i * i <= n; i++) { // TC: O(Sqrt(N))
            if (n % i == 0) {
                countDivs++;
            }
        }
        return countDivs == 0;
    }
}
