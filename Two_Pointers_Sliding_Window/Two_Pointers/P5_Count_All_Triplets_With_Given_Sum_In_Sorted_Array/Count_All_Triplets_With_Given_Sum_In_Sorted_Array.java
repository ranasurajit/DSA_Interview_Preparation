package Two_Pointers_Sliding_Window.Two_Pointers.P5_Count_All_Triplets_With_Given_Sum_In_Sorted_Array;

public class Count_All_Triplets_With_Given_Sum_In_Sorted_Array {
    public static void main(String[] args) {
        Count_All_Triplets_With_Given_Sum_In_Sorted_Array solution = new Count_All_Triplets_With_Given_Sum_In_Sorted_Array();

        int[] arr = { -3, -1, -1, 0, 1, 2 };
        int target = -2;

        int countBruteForce = solution.countTripletsBruteForce(arr, target);
        System.out.println(countBruteForce);

        int countOptimal = solution.countTripletsOptimal(arr, target);
        System.out.println(countOptimal);
    }

    /**
     * Approach II : Using Two Pointers Approach (As array is sorted)
     * 
     * TC: O(N ^ 2)
     * SC: O(1)
     */
    public int countTripletsOptimal(int[] arr, int target) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n - 2; i++) { // TC: O(N)
            int p = i + 1;
            int q = n - 1;
            while (p < q) { // TC: O(N)
                int sum = arr[i] + arr[p] + arr[q];
                if (sum == target) {
                    count++;
                    int temp = p + 1;
                    while (temp < q && arr[temp] == arr[temp - 1]) {
                        count++;
                        temp++;
                    }
                    q--;
                } else if (sum < target) {
                    p++;
                } else if (sum > target) {
                    q--;
                }
            }
        }
        return count;
    }

    /**
     * Approach I : Using Simulation Approach
     * 
     * TC: O(N ^ 3)
     * SC: O(1)
     */
    public int countTripletsBruteForce(int[] arr, int target) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n - 2; i++) { // TC: O(N)
            for (int j = i + 1; j < n - 1; j++) { // TC: O(N)
                for (int k = j + 1; k < n; k++) { // TC: O(N)
                    if (arr[i] + arr[j] + arr[k] == target) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
