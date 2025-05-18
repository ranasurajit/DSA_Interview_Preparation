package Binary_Search.One_Dimensional.P5_Number_Of_Occurrence;

public class Number_Of_Occurrence {
    public static void main(String[] args) {
        Number_Of_Occurrence solution = new Number_Of_Occurrence();

        int[] arr = { 8, 9, 10, 12, 12, 12 };
        int target = 12;

        int frequency = solution.countFreq(arr, target);
        System.out.println(frequency);
    }

    /**
     * Approach : Using Binary Search Approach
     * 
     * TC: O(2 x log(N)) ~ O(log(N))
     * SC: O(1)
     */
    int countFreq(int[] arr, int target) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        return upperBound(arr, low, high, target) -
                lowerBound(arr, low, high, target);
    }

    /**
     * Using Binary Search Approach (arr[i] >= target)
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private int lowerBound(int[] arr, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Using Binary Search Approach (arr[i] > target)
     * 
     * TC: O(log(N))
     * SC: O(1)
     */
    private int upperBound(int[] arr, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
