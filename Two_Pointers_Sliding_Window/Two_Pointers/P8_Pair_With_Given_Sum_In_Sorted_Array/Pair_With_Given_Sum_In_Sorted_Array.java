package Two_Pointers_Sliding_Window.Two_Pointers.P8_Pair_With_Given_Sum_In_Sorted_Array;

public class Pair_With_Given_Sum_In_Sorted_Array {
    public static void main(String[] args) {
        Pair_With_Given_Sum_In_Sorted_Array solution = new Pair_With_Given_Sum_In_Sorted_Array();

        int[] arr = { -1, 1, 5, 5, 7 };
        int target = 6;

        int pairs = solution.countPairs(arr, target);
        System.out.println(pairs);
    }

    /**
     * Approach : Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    int countPairs(int arr[], int target) {
        int n = arr.length;
        int p = 0;
        int q = n - 1;
        int count = 0;
        while (p < q) { // TC: O(N)
            int sum = arr[p] + arr[q];
            if (sum == target) {
                count++;
                // check for duplicate numbers
                int temp = p + 1;
                while (temp < q && arr[temp] == arr[temp - 1]) {
                    count++;
                    temp++;
                }
                q--;
            } else if (sum < target) {
                p++;
            } else {
                q--;
            }
        }
        return count;
    }
}
