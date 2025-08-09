package BitManipulation.Medium.P6_Minimum_Flips_To_Make_A_OR_B_Equal_To_C;

public class Minimum_Flips_To_Make_A_OR_B_Equal_To_C {
    public static void main(String[] args) {
        Minimum_Flips_To_Make_A_OR_B_Equal_To_C solution = new Minimum_Flips_To_Make_A_OR_B_Equal_To_C();

        int a1 = 2, b1 = 6, c1 = 5;
        int minFlips1 = solution.minFlips(a1, b1, c1);
        System.out.println(minFlips1);

        int a2 = 4, b2 = 2, c2 = 7;
        int minFlips2 = solution.minFlips(a2, b2, c2);
        System.out.println(minFlips2);

        int a3 = 1, b3 = 2, c3 = 3;
        int minFlips3 = solution.minFlips(a3, b3, c3);
        System.out.println(minFlips3);
    }

    /**
     * Approach : Using Bit-Manipulation Approach
     * 
     * TC: O(32) ~ O(1)
     * SC: O(1)
     */
    public int minFlips(int a, int b, int c) {
        int flips = 0;
        for (int i = 31; i >= 0; i--) { // TC: O(32)
            int aiBit = ((a >> i) & 1);
            int biBit = ((b >> i) & 1);
            int ciBit = ((c >> i) & 1);
            if ((aiBit | biBit) == ciBit) {
                continue;
            }
            if (ciBit == 0) {
                if (aiBit != 0) {
                    flips++;
                }
                if (biBit != 0) {
                    flips++;
                }
            } else {
                if (aiBit != 1 || biBit != 1) {
                    flips++;
                }
            }
        }
        return flips++;
    }
}
