package Binary_Search_Backup.One_Dimentional_Arrays.P9_Number_Of_Occurrence;

public class Number_Of_Occurrence {
    public static void main(String[] args) {
        Number_Of_Occurrence solution = new Number_Of_Occurrence();

        int[] arr1 = { 8, 9, 10, 12, 12, 12 };
        int target1 = 12;
        int freq1 = solution.countFreq(arr1, target1);
        System.out.println(freq1);

        int[] arr2 = { 1, 1, 2, 2, 2, 2, 3 };
        int target2 = 4;
        int freq2 = solution.countFreq(arr2, target2);
        System.out.println(freq2);
    }

    /**
     * Using Binary Search
     * 
     * TC: O(2 x log(N)) ~ O(log(N))
     * SC: O(1)
     * 
     * @param arr
     * @param target
     * @return
     */
    private int countFreq(int[] arr, int target) {
        int n = arr.length;
        int lBound = lowerBound(arr, n, target);
        if (lBound == n) {
            return 0;
        }
        int uBound = upperBound(arr, n, target);
        return uBound - lBound;
    }

    /**
     * TC: O(log(N))
     * SC: O(1)
     * 
     * @param arr
     * @param n
     * @param x
     * @return
     */
    private int lowerBound(int arr[], int n, int x) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * TC: O(log(N))
     * SC: O(1)
     * 
     * @param arr
     * @param n
     * @param x
     * @return
     */
    private int upperBound(int arr[], int n, int x) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
