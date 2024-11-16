package Binary_Search.Binary_Search_On_Answers.P2_Nth_Root_Number;

public class Nth_Root_Number {
    public static void main(String[] args) {
        int n = 3;
        int m = 27;

        int rootBruteForce = NthRootBruteForce(n, m);
        System.out.println(rootBruteForce);

        int rootOptimal = NthRootrootOptimal(n, m);
        System.out.println(rootOptimal);
    }

    /**
     * Optimal Approach
     * 
     * TC: O(log(M))
     * SC: O(1)
     * 
     * @param n
     * @param m
     * @return
     */
    private static int NthRootrootOptimal(int n, int m) {
        int low = 1;
        int high = m;
        while (low <= high) { // TC: O(log(M))
            int mid = low + (high - low) / 2;
            int pow = multiple(mid, n);
            if (pow == m) {
                return mid;
            } else if (pow > m) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Brute-Force Approach
     * 
     * TC: O(M x log(N))
     * SC: O(1)
     * 
     * @param n
     * @param m
     * @return
     */
    private static int NthRootBruteForce(int n, int m) {
        for (int i = 1; i <= m; i++) { // TC: O(M)
            int pow = multiple(i, n);
            if (pow == m) { // TC: O(log(N))
                return i;
            } else if (pow > m) {
                break;
            }
        }
        return -1;
    }

    /**
     * TC: log(N)(base 2)
     * SC: O(1)
     * 
     * @param current
     * @param n
     * @return
     */
    private static int multiple(int current, int n) {
        return (int) Math.pow(current, n);
    }
}
