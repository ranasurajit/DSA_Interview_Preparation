package Math_Combinatorics.Basic_Math.P1_Count_Digits;

public class Count_Digits {
    public static void main(String[] args) {
        Count_Digits solution = new Count_Digits();

        int n1 = 12;
        int countDigits1 = solution.countDigits(n1);
        System.out.println(countDigits1);

        int n2 = 456;
        int countDigits2 = solution.countDigits(n2);
        System.out.println(countDigits2);
    }

    /**
     * Approach : Using Math Approach
     * 
     * TC: O(log(N) Base 10)
     * SC: O(1)
     */
    public int countDigits(int n) {
        int count = 0;
        while (n > 0) {
            n = n / 10;
            count++;
        }
        return count;
    }
}
