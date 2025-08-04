package Math_Combinatorics.Basic_Math.P2_Count_Digits_With_Divisible_Digits;

public class Count_Digits_With_Divisible_Digits {
    public static void main(String[] args) {
        Count_Digits_With_Divisible_Digits solution = new Count_Digits_With_Divisible_Digits();

        int n1 = 336;
        int countDigits1 = solution.countDigits(n1);
        System.out.println(countDigits1);

        int n2 = 35;
        int countDigits2 = solution.countDigits(n2);
        System.out.println(countDigits2);
    }

    /**
     * Approach : Using Math Approach
     * 
     * TC: O(log(N) Base 10) + O(10) ~ O(log(N) Base 10)
     * SC: O(10) ~ O(1)
     */
    public int countDigits(int n) {
        int count = 0;
        int[] numMap = new int[10]; // SC: O(10)
        int num = n;
        while (n > 0) { // TC: O(log(N) Base 10)
            int rem = n % 10;
            numMap[rem]++;
            n = n / 10;
        }
        for (int i = 1; i < 10; i++) { // TC: O(10)
            if (numMap[i] > 0 && num % i == 0) {
                count += numMap[i];
            }
        }
        return count;
    }
}
