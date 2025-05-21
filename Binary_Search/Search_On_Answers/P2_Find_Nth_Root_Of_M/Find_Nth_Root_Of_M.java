package Binary_Search.Search_On_Answers.P2_Find_Nth_Root_Of_M;

public class Find_Nth_Root_Of_M {
    public static void main(String[] args) {
        Find_Nth_Root_Of_M solution = new Find_Nth_Root_Of_M();

        int n1 = 2, m1 = 9;
        int rootValue1 = solution.nthRoot(n1, m1);
        System.out.println(rootValue1);

        int n2 = 3, m2 = 9;
        int rootValue2 = solution.nthRoot(n2, m2);
        System.out.println(rootValue2);
    }

    /**
     * Approach : Using Binary Search Approach
     * 
     * TC: O(log(M) x log(N))
     * SC: O(log(N))
     */
    public int nthRoot(int n, int m) {
        if (n == 1) {
            return m;
        }
        long low = 1L;
        long high = (long) m;
        while (low <= high) { // TC: O(log(M))
            long mid = low + (high - low) / 2;
            long prod = fastPower(mid, n); // TC: O(log(N)), SC: O(log(N))
            if (prod == m) {
                return (int) mid;
            } else if (prod < m) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Using Recursion Approach
     * 
     * TC: O(log(N))
     * SC: O(log(N))
     */
    private long fastPower(long mid, int n) {
        if (n == 1) {
            return mid;
        }
        long ans = fastPower(mid, n / 2);
        long result = ans * ans;
        if ((n & 1) != 0) {
            // odd power
            result = result * mid;
        }
        return result;
    }
}
