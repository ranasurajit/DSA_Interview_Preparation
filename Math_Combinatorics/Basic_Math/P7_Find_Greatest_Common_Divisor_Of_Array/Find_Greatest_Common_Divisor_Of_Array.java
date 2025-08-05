package Math_Combinatorics.Basic_Math.P7_Find_Greatest_Common_Divisor_Of_Array;

public class Find_Greatest_Common_Divisor_Of_Array {
    public static void main(String[] args) {
        Find_Greatest_Common_Divisor_Of_Array solution = new Find_Greatest_Common_Divisor_Of_Array();

        int[] nums1 = { 2, 5, 6, 9, 10 };
        int gcd1 = solution.findGCD(nums1);
        System.out.println(gcd1);

        int[] nums2 = { 7, 5, 6, 8, 3 };
        int gcd2 = solution.findGCD(nums2);
        System.out.println(gcd2);

        int[] nums3 = { 3, 3 };
        int gcd3 = solution.findGCD(nums3);
        System.out.println(gcd3);
    }

    /**
     * Approach : Using Euler's Euclidean GCD Algorithm Approach
     * 
     * TC: O(N) + O(log(Min(nums)))
     * SC: O(log(Min(nums)))
     */
    public int findGCD(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int x : nums) { // TC: O(N)
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
        return gcd(max, min); // TC: O(log(Min(a, b))), SC: O(log(Min(a, b)))
    }

    /**
     * Using Euler's Euclidean GCD Algorithm Approach
     * 
     * TC: O(log(Min(a, b)))
     * SC: O(log(Min(a, b)))
     */
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
