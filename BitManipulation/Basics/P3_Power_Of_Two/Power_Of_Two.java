package BitManipulation.Basics.P3_Power_Of_Two;

public class Power_Of_Two {
    public static void main(String[] args) {
        Power_Of_Two solution = new Power_Of_Two();

        int n1 = 1;
        boolean isPowerOfTwo1 = solution.isPowerOfTwoApproachI(n1);
        System.out.println(isPowerOfTwo1);

        int n2 = 16;
        boolean isPowerOfTwo2 = solution.isPowerOfTwoApproachII(n2);
        System.out.println(isPowerOfTwo2);

        int n3 = 3;
        boolean isPowerOfTwo3 = solution.isPowerOfTwoOptimal(n3);
        System.out.println(isPowerOfTwo3);
    }

    /**
     * Approach III : Using Bit-Manipulation (Shortcut) Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    public boolean isPowerOfTwoOptimal(int n) {
        if (n <= 0) {
            return false;
        }
        /**
         * if n is a power of 2 then it will be form 00010000
         * and (n - 1) will be 00001000 so (n & (n - 1)) = 0
         */
        return (n & (n - 1)) == 0;
    }

    /**
     * Approach II : Using Bit-Manipulation (Cleaner) Approach
     * 
     * TC: O(32)
     * SC: O(1)
     */
    public boolean isPowerOfTwoApproachII(int n) {
        if (n < 0) {
            return false;
        }
        int countSetBits = 0;
        while (n > 0) { // TC: O(32)
            countSetBits += (n & 1);
            n = n >> 1;
        }
        // for a number to be power of 2 it should have exactly 1 set bit
        return countSetBits == 1;
    }

    /**
     * Approach I : Using Bit-Manipulation Approach
     * 
     * TC: O(32) ~ O(1)
     * SC: O(1)
     */
    public boolean isPowerOfTwoApproachI(int n) {
        if (n < 0) {
            return false;
        }
        int countSetBits = 0;
        for (int i = 0; i < 32; i++) { // TC: O(32)
            int ithBit = ((n >> i) & 1);
            if (ithBit == 1) {
                countSetBits++;
            }
        }
        // for a number to be power of 2 it should have exactly 1 set bit
        return countSetBits == 1;
    }
}
