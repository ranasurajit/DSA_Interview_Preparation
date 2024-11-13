package Binary_Search.One_Dimentional_Arrays.P3_Upper_Bound;

public class Implement_Upper_Bound {
    public static void main(String[] args) {
        int[] arr = { 2, 4, 6, 7 };
        int n = 4;
        int x = 5;
        int uBound = upperBound(arr, n, x);
        System.out.println(uBound);
    }

    /**
     * We need to find index such that arr[i] > x (strictly greater)
     * 
     * TC: O(log(N))
     * SC: O(1)
     * 
     * @param arr
     * @param x
     * @param n
     * @return
     */
    public static int upperBound(int[] arr, int x, int n) {
        int low = 0;
        int high = n - 1;
        int answer = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > x) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }
}
