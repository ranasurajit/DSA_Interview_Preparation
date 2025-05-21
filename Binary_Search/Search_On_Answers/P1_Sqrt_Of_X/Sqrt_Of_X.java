package Binary_Search.Search_On_Answers.P1_Sqrt_Of_X;

public class Sqrt_Of_X {
    public static void main(String[] args) {
        Sqrt_Of_X solution = new Sqrt_Of_X();

        int x1 = 4;
        int srqt1 = solution.mySqrt(x1);
        System.out.println(srqt1);

        int x2 = 8;
        int srqt2 = solution.mySqrt(x2);
        System.out.println(srqt2);
    }

    /**
     * Approach : Binary Search on Answers Approach
     *
     * TC: O(log(X))
     * SC: O(1)
     */
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        long low = 1;
        long high = x;
        while (low <= high) { // TC: O(log(X))
            long mid = low + (high - low) / 2;
            if (mid * mid > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (int) high;
    }
}
