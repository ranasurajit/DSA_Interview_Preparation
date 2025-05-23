package Binary_Search.Multiple_Arrays.P2_Kth_Element_Of_Two_Arrays;

public class Kth_Element_Of_Two_Arrays {
    public static void main(String[] args) {
        Kth_Element_Of_Two_Arrays solution = new Kth_Element_Of_Two_Arrays();

        int[] a = { 100, 112, 256, 349, 770 };
        int[] b = { 72, 86, 113, 119, 265, 445, 892 };
        int k = 7;

        int kthItem = solution.kthElement(a, b, k);
        System.out.println(kthItem);
    }

    /**
     * Approach : Using Binary Search Approach
     * 
     * TC: O(log(Min(N1, N2)))
     * SC: O(1)
     */
    public int kthElement(int a[], int b[], int k) {
        int n1 = a.length;
        int n2 = b.length;
        // ensure that array 'a' is the smaller array
        if (n1 > n2) {
            return kthElement(b, a, k);
        }
        // now array 'a' is the smallest array with size n1
        int low = Math.max(0, k - n2); // if k > n1 then we need atleast (k - n2) elements from 'b'
        int high = Math.min(k, n1); // cannot pick up more than k elements from 'a'
        // Applying Binary Search
        while (low <= high) { // TC: O(log(Min(N1, N2)))
            int mid1 = low + (high - low) / 2;
            // finding all the range values
            int mid2 = k - mid1;
            int l1 = mid1 > 0 ? a[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = mid2 > 0 ? b[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = mid1 < n1 ? a[mid1] : Integer.MAX_VALUE;
            int r2 = mid2 < n2 ? b[mid2] : Integer.MAX_VALUE;
            // Applying Binary Search conditions
            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            } else if (l1 > r2) {
                high = mid1 - 1;
            } else if (l2 > r1) {
                low = mid1 + 1;
            }
        }
        return 0;
    }
}
