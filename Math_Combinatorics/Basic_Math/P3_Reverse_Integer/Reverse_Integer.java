package Math_Combinatorics.Basic_Math.P3_Reverse_Integer;

public class Reverse_Integer {
    public static void main(String[] args) {
        Reverse_Integer solution = new Reverse_Integer();

        int x1 = 123;
        int reverseX1 = solution.reverse(x1);
        System.out.println(reverseX1);

        int x2 = -123;
        int reverseX2 = solution.reverse(x2);
        System.out.println(reverseX2);

        int x3 = 120;
        int reverseX3 = solution.reverse(x3);
        System.out.println(reverseX3);
    }

    /**
     * Approach : Using Math Approach
     * 
     * TC: O(log(N) Base 10)
     * SC: O(1)
     */
    public int reverse(int x) {
        long rev = 0L;
        int rem = 0;
        int neg = x < 0 ? -1 : 1;
        x = x * neg;
        while (x > 0) { // TC: O(log(N) Base 10)
            rem = x % 10;
            rev = rev * 10 + rem;
            x = x / 10;
        }
        if (rev > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) rev * neg;
    }
}
