package Two_Pointers.P5_Container_With_Most_Water;

public class Container_With_Most_Water {
    public static void main(String[] args) {
        Container_With_Most_Water solution = new Container_With_Most_Water();

        int[] arr = { 2, 1, 8, 6, 4, 6, 5, 5 };
        int max = solution.maxWater(arr);
        System.out.println(max);
    }

    /**
     * Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * @param arr
     * @return
     */
    private int maxWater(int arr[]) {
        int n = arr.length;
        int i = 0; // start pointer
        int j = n - 1; // end pointer
        int max = 0;
        int current = 0;
        while (i < j) { // TC: O(N)
            if (arr[i] < arr[j]) {
                current = (j - i) * arr[i];
                i++;
            } else {
                current = (j - i) * arr[j];
                j--;
            }
            max = Math.max(max, current);
        }
        return max;
    }
}
