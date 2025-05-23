package Binary_Search.Multiple_Arrays.P1_Median_Of_Two_Sorted_Arrays;

public class Median_Of_Two_Sorted_Arrays {
    public static void main(String[] args) {
        Median_Of_Two_Sorted_Arrays solution = new Median_Of_Two_Sorted_Arrays();

        int[] nums1 = { 1, 2 };
        int[] nums2 = { 3, 4 };

        double medianApproachI = solution.findMedianSortedArraysApproachI(nums1, nums2);
        System.out.println(medianApproachI);

        double medianApproachII = solution.findMedianSortedArraysApproachII(nums1, nums2);
        System.out.println(medianApproachII);

        double medianApproachIII = solution.findMedianSortedArraysOptimal(nums1, nums2);
        System.out.println(medianApproachIII);
    }

    /**
     * Approach III : Using Binary Search Approach
     *
     * TC: O(log(Min(N1, N2)))
     * SC: O(1)
     */
    public double findMedianSortedArraysOptimal(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        // ensuring that nums1 is always the smaller size array than nums2
        if (n1 > n2) {
            return findMedianSortedArraysOptimal(nums2, nums1);
        }
        int low = 0; // min count of elements from nums1
        int high = n1; // max count of elements from nums1
        int left = (n1 + n2 + 1) / 2;
        while (low <= high) { // TC: O(log(Min(N1, N2)))
            int mid1 = low + (high - low) / 2;
            // finding all the range values
            int mid2 = left - mid1;
            int l1 = (mid1 > 0) ? nums1[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? nums2[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < n1) ? nums1[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < n2) ? nums2[mid2] : Integer.MAX_VALUE;

            // Applying Binary Search conditions
            if (l1 <= r2 && l2 <= r1) {
                if (((n1 + n2) & 1) == 0) {
                    // even length
                    return (double) (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    // odd length
                    return (double) Math.max(l1, l2);
                }
            } else if (l1 > r2) {
                high = mid1 - 1;
            } else if (l2 > r1) {
                low = mid1 + 1;
            }
        }
        return 0.0;
    }

    /**
     * Approach II : Using Better (Two Pointers) Approach
     *
     * TC: O(M + N)
     * SC: O(1)
     */
    public double findMedianSortedArraysApproachII(int[] nums1, int[] nums2) {
        int m1 = nums1.length;
        int m2 = nums2.length;

        int n = m1 + m2;
        int second = n / 2;
        int first = second - 1;
        int median1 = 0;
        int median2 = 0;

        int p = 0; // pointer at the start of array 'nums1'
        int q = 0; // pointer at the start of array 'nums2'
        int r = 0; // count of the resultant array index

        while (p < m1 && q < m2) { // TC: O(M + N)
            if (nums1[p] < nums2[q]) {
                if (r == first) {
                    median1 = nums1[p];
                }
                if (r == second) {
                    median2 = nums1[p];
                }
                r++;
                p++;
            } else {
                if (r == first) {
                    median1 = nums2[q];
                }
                if (r == second) {
                    median2 = nums2[q];
                }
                r++;
                q++;
            }
        }
        while (p < m1) {
            if (r == first) {
                median1 = nums1[p];
            }
            if (r == second) {
                median2 = nums1[p];
            }
            r++;
            p++;
        }
        while (q < m2) {
            if (r == first) {
                median1 = nums2[q];
            }
            if (r == second) {
                median2 = nums2[q];
            }
            r++;
            q++;
        }
        return ((m1 + m2) & 1) == 0 ? (double) (median1 + median2) / 2.0 : (double) median2;
    }

    /**
     * Approach I : Using Brute-Force (Two Pointers) Approach
     *
     * TC: O(M + N)
     * SC: O(M + N)
     */
    public double findMedianSortedArraysApproachI(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        double[] result = new double[m + n]; // SC: O(M + N)
        int p = 0; // pointer at the start of array 'nums1'
        int q = 0; // pointer at the start of array 'nums2'
        int r = 0; // pointer at the start of array 'result'

        while (p < m && q < n) { // TC: O(M + N)
            if (nums1[p] < nums2[q]) {
                result[r] = (double) nums1[p];
                r++;
                p++;
            } else {
                result[r] = (double) nums2[q];
                r++;
                q++;
            }
        }
        while (p < m) {
            result[r] = (double) nums1[p];
            r++;
            p++;
        }
        while (q < n) {
            result[r] = (double) nums2[q];
            r++;
            q++;
        }
        int len = result.length;
        return (len & 1) == 0 ? (result[len / 2] + result[(len / 2) - 1]) / 2 : result[len / 2];
    }
}
