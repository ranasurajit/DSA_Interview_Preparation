package Binary_Search.One_Dimentional_Arrays.P2_Lower_Bound;

public class Implement_Lower_Bound {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 2, 3, 3, 5 };
        int n = 6;
        int x = 2;
        int lBound = lowerBound(arr, n, x);
        System.out.println(lBound);
    }

    /**
     * We need to find index where arr[i] >= x
     * 
     * TC: O(log(N))
     * SC: O(1)
     * 
     * @param arr
     * @param n
     * @param x
     * @return
     */
    public static int lowerBound(int[] arr, int n, int x) {
        int low = 0;
        int high = n - 1;
        int answer = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= x) {
                answer = mid; // best answer when high is decremented as below
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }
}
