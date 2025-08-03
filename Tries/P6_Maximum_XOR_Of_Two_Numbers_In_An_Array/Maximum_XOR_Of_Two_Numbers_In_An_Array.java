package Tries.P6_Maximum_XOR_Of_Two_Numbers_In_An_Array;

public class Maximum_XOR_Of_Two_Numbers_In_An_Array {
    BitTrieNode root = new BitTrieNode();

    public static void main(String[] args) {
        Maximum_XOR_Of_Two_Numbers_In_An_Array solution = new Maximum_XOR_Of_Two_Numbers_In_An_Array();

        int[] nums1 = { 3, 10, 5, 25, 2, 8 };
        int maximumXOR1 = solution.findMaximumXOR(nums1);
        System.out.println(maximumXOR1);

        int[] nums2 = { 14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70 };
        int maximumXOR2 = solution.findMaximumXOR(nums2);
        System.out.println(maximumXOR2);
    }

    /**
     * Approach : Using Bit-Trie Approach
     *
     * TC: O(32 x N) + O(32 x N) ~ O(N)
     * SC: O(1)
     */
    public int findMaximumXOR(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) { // TC: O(N)
            insert(nums[i]); // TC: O(32)
        }
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) { // TC: O(N)
            maxValue = Math.max(maxValue, getMaxValueFromTrie(nums[i])); // TC: O(32)
        }
        return maxValue;
    }

    /**
     * Using Bit-Trie Approach
     *
     * TC: O(32)
     * SC: O(1)
     */
    private int getMaxValueFromTrie(int num) {
        int maxXOR = 0;
        BitTrieNode crawl = root;
        for (int i = 31; i >= 0; i--) {
            int ithBit = ((num >> i) & 1);
            // if ithBit = 0 we will try to XOR it with 1, else with 0
            if (ithBit == 0) {
                // we would like to look for right child
                if (crawl.right != null) {
                    maxXOR += (1 << i);
                    crawl = crawl.right;
                } else {
                    crawl = crawl.left;
                }
            } else {
                // we would like to look for left child
                if (crawl.left != null) {
                    crawl = crawl.left;
                    maxXOR += (1 << i);
                } else {
                    crawl = crawl.right;
                }
            }
        }
        return maxXOR;
    }

    /**
     * Using Bit-Trie Approach
     *
     * TC: O(32)
     * SC: O(1)
     */
    private void insert(int num) {
        BitTrieNode crawl = root;
        for (int i = 31; i >= 0; i--) { // TC: O(32)
            // check if ith bit is set or not set
            int ithBit = ((num >> i) & 1);
            if (ithBit == 0) {
                // move to left child
                if (crawl.left == null) {
                    crawl.left = new BitTrieNode();
                }
                crawl = crawl.left;
            } else {
                // move to right child
                if (crawl.right == null) {
                    crawl.right = new BitTrieNode();
                }
                crawl = crawl.right;
            }
        }
    }

    class BitTrieNode {
        BitTrieNode left;
        BitTrieNode right;
    }
}
