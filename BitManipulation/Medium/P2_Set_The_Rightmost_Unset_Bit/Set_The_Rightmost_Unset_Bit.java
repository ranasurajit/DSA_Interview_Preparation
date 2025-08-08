package BitManipulation.Medium.P2_Set_The_Rightmost_Unset_Bit;

public class Set_The_Rightmost_Unset_Bit {
    public static void main(String[] args) {
        Set_The_Rightmost_Unset_Bit solution = new Set_The_Rightmost_Unset_Bit();

        int N1 = 10;
        int numberAfterBitSet1 = solution.setBits(N1);
        System.out.println(numberAfterBitSet1);

        int N2 = 7;
        int numberAfterBitSet2 = solution.setBits(N2);
        System.out.println(numberAfterBitSet2);
    }

    /**
     * Approach : Using Bit-Manipulation (Brute-Force) Approach
     * 
     * TC: O(32) ~ O(1)
     * SC: O(1)
     */
    public int setBits(int N) {
        int idx = -1;
        int leftMostSetBit = 0;
        for (int i = 31; i >= 0; i--) { // TC: O(32)
            if ((N & (1 << i)) != 0) {
                leftMostSetBit = i;
                break;
            }
        }
        for (int i = 0; i < 32; i++) { // TC: O(32)
            int ithBit = ((N >> i) & 1);
            if (ithBit != 1) {
                idx = i;
                break;
            }
        }
        return idx > leftMostSetBit ? N : (N | (1 << idx));
    }
}
