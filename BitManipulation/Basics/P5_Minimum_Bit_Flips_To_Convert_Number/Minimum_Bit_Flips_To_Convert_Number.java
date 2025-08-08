package BitManipulation.Basics.P5_Minimum_Bit_Flips_To_Convert_Number;

public class Minimum_Bit_Flips_To_Convert_Number {
    public static void main(String[] args) {
        Minimum_Bit_Flips_To_Convert_Number solution = new Minimum_Bit_Flips_To_Convert_Number();

        int start1 = 10, goal1 = 7;
        int bitFlips1 = solution.minBitFlipsBruteForce(start1, goal1);
        System.out.println(bitFlips1);

        int start2 = 3, goal2 = 4;
        int bitFlips2 = solution.minBitFlipsOptimal(start2, goal2);
        System.out.println(bitFlips2);
    }

    /**
     * Approach II : Using Bit-Manipulation (Better) Approach
     * 
     * TC: O(32) ~ O(1)
     * SC: O(1)
     */
    public int minBitFlipsOptimal(int start, int goal) {
        int flip = 0;
        /**
         * we need to find the count of bit difference between start and goal
         * so XOR of start and goal will have bit set when there is a difference
         * so we can count number of set bits in XOR(start, goal)
         */
        int xor = start ^ goal;
        for (int i = 0; i < 32; i++) { // TC: O(32)
            int ithBit = ((xor >> i) & 1);
            if (ithBit == 1) {
                // we need to flip
                flip++;
            }
        }
        return flip;
    }

    /**
     * Approach I : Using Bit-Manipulation Approach
     * 
     * TC: O(32) ~ O(1)
     * SC: O(1)
     */
    public int minBitFlipsBruteForce(int start, int goal) {
        int flip = 0;
        for (int i = 0; i < 32; i++) { // TC: O(32)
            int siBit = ((start >> i) & 1);
            int giBit = ((goal >> i) & 1);
            if (siBit != giBit) {
                // we need to flip
                flip++;
            }
        }
        return flip;
    }
}
