package BitManipulation.Basics.P1_Check_Kth_Bit;

public class Check_Kth_Bit {
    public static void main(String[] args) {
        Check_Kth_Bit solution = new Check_Kth_Bit();

        int n1 = 4;
        int k1 = 2;
        boolean isKthBitSet1 = solution.checkKthBit(n1, k1);
        System.out.println(isKthBitSet1);

        int n2 = 500;
        int k2 = 3;
        boolean isKthBitSet2 = solution.checkKthBit(n2, k2);
        System.out.println(isKthBitSet2);
    }

    /**
     * Approach : Using Bit-Manipulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private boolean checkKthBit(int n, int k) {
        return ((n >> k) & 1) == 1;
    }
}
