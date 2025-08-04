package Math_Combinatorics.Basic_Math.P5_Armstrong_Numbers;

public class Armstrong_Numbers {
    public static void main(String[] args) {
        Armstrong_Numbers solution = new Armstrong_Numbers();

        int n1 = 153;
        boolean isArmstrongNumber1 = solution.armstrongNumber(n1);
        System.out.println(isArmstrongNumber1);

        int n2 = 372;
        boolean isArmstrongNumber2 = solution.armstrongNumber(n2);
        System.out.println(isArmstrongNumber2);

        int n3 = 100;
        boolean isArmstrongNumber3 = solution.armstrongNumber(n3);
        System.out.println(isArmstrongNumber3);
    }

    /**
     * Approach : Using Math Approach
     * 
     * TC: O(log(N) Base 10)
     * SC: O(1)
     */
    private boolean armstrongNumber(int n) {
        int num = n; // storing a backup as we will do some operations on n
        long sum = 0L;
        while (n > 0) {
            int rem = n % 10; // TC: O(log(N) Base 10)
            sum += (rem * rem * rem);
            n = n / 10;
        }
        return (int) sum == num;
    }
}
