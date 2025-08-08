package BitManipulation.Medium.P1_Find_XOR_Of_Numbers_From_L_To_R;

public class Find_XOR_Of_Numbers_From_L_To_R {
    public static void main(String[] args) {
        Find_XOR_Of_Numbers_From_L_To_R solution = new Find_XOR_Of_Numbers_From_L_To_R();

        int L1 = 4, R1 = 8;
        int rangeXOR1 = solution.findXORBruteForce(L1, R1);
        System.out.println(rangeXOR1);

        int L2 = 9, R2 = 20;
        int rangeXOR2 = solution.findXOROptimal(L2, R2);
        System.out.println(rangeXOR2);
    }

    /**
     * Approach II : Using Bit-Manipulation (Optimal By Observation) Approach
     * 
     * TC: O(1)
     * SC: O(1)
     * 
     * Accepted (2005 / 2005 testcases passed)
     */
    public int findXOROptimal(int l, int r) {
        /**
         * XOR till N :
         * 
         * N % 4 == 1 -> XOR = 1
         * N % 4 == 2 -> XOR = N + 1
         * N % 4 == 3 -> XOR = 0
         * N % 4 == 0 -> XOR = N
         * 
         * 
         * also for Range (L to R)
         * 
         * XOR(R) = 1 ^ 2 ^ 3 ^ .... ^ (L - 1) ^ L ^ .... ^ R
         * XOR(L) = 1 ^ 2 ^ 3 ^ .....^ (L - 1) ^ L
         * 
         * XOR (R - L) = XOR(L - 1) ^ XOR(R)
         */
        return xor(r) ^ xor(l - 1);
    }

    /**
     * Utility To find XOR value from (1 to n)
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private int xor(int n) {
        if (n % 4 == 0) {
            return n;
        } else if (n % 4 == 1) {
            return 1;
        } else if (n % 4 == 2) {
            return n + 1;
        } else {
            return 0;
        }
    }

    /**
     * Approach I : Using Bit-Manipulation (Brute-Force) Approach
     * 
     * TC: O(R - L)
     * SC: O(1)
     * 
     * Time Limit Exceeded (1005 / 2005 testcases passed)
     */
    public int findXORBruteForce(int l, int r) {
        int xor = 0;
        for (int i = l; i <= r; i++) {
            xor ^= i;
        }
        return xor;
    }
}
