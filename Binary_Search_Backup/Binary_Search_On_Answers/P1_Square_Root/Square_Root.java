package Binary_Search_Backup.Binary_Search_On_Answers.P1_Square_Root;

public class Square_Root {
    public static void main(String[] args) {
        int n = 5;

        int sqrtBruteForce = floorSqrtBruteForce(n);
        System.out.println(sqrtBruteForce);

        int sqrtOptimal = floorSqrtOptimal(n);
        System.out.println(sqrtOptimal);
    }

    /**
     * Optimal Approach
     * 
     * TC: O(log(N))
     * SC: O(1)
     * 
     * @param n
     * @return
     */
    private static int floorSqrtOptimal(int n) {
        int max = Integer.MIN_VALUE;
        int low = 0;
        int high = n;
        while (low <= high) { // TC: O(log(N))
            int mid = low + (high - low) / 2;
            if (calculateProduct(mid) <= n) { // TC: O(1)
                max = Math.max(max, mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return max;
    }

    /**
     * Brute-Force Approach
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * @param n
     * @return
     */
    private static int floorSqrtBruteForce(int n) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= n; i++) { // TC: O(N)
            if (calculateProduct(i) <= n) {
                max = Math.max(max, i);
            }
        }
        return max;
    }

    private static int calculateProduct(int current) {
        return current * current;
    }
}
