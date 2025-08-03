package Tries.P7_Maximum_XOR_With_An_Element_From_Array;

import java.util.Arrays;

public class Maximum_XOR_With_An_Element_From_Array {
    public static void main(String[] args) {
        Maximum_XOR_With_An_Element_From_Array solution = new Maximum_XOR_With_An_Element_From_Array();

        int[] nums1 = { 0, 1, 2, 3, 4 };
        int[][] queries1 = { { 3, 1 }, { 1, 3 }, { 5, 6 } };
        int[] maxXORQueryResult1 = solution.maximizeXor(nums1, queries1);
        System.out.println(Arrays.toString(maxXORQueryResult1));

        int[] nums2 = { 5, 2, 4, 6, 6, 3 };
        int[][] queries2 = { { 12, 4 }, { 8, 1 }, { 6, 3 } };
        int[] maxXORQueryResult2 = solution.maximizeXor(nums2, queries2);
        System.out.println(Arrays.toString(maxXORQueryResult2));
    }

    /**
     * Approach : Using Bit-Trie Approach
     *
     * TC: O(N x log(N)) + O(Q) + O(Q x log(Q)) + O(Q + N) ~ O(N x log(N) + Q x
     * log(Q))
     * SC: O(Q + N x 32) ~ O(Q + N)
     */
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = nums.length;
        int q = queries.length;
        /**
         * We will sort the array 'nums' and sort array 'queries'
         * based upon increasing order of queries[i][1] so that
         * we can execute offline queries but we need to store
         * their actual indices
         */
        Arrays.sort(nums); // TC: O(N x log(N))
        int[][] queryList = new int[q][3];
        for (int i = 0; i < q; i++) { // TC: O(Q)
            queryList[i] = new int[] { queries[i][0], queries[i][1], i };
        }
        Arrays.sort(queryList, (a, b) -> a[1] - b[1]); // TC: O(Q x log(Q))
        BitTrie trie = new BitTrie();
        int[] result = new int[q];
        int i = 0;
        for (int[] query : queryList) { // TC: O(Q)
            int num = query[0];
            int maxIdx = query[1];
            int resIdx = query[2];
            while (i < n && nums[i] <= maxIdx) { // TC: O(N)
                trie.insert(nums[i]); // TC: O(32)
                i++;
            }
            result[resIdx] = i == 0 ? -1 : trie.getMaxXOR(num); // TC: O(32)
        }
        return result;
    }

    class BitTrie {
        BitTrieNode root = new BitTrieNode();

        /**
         * Using Bit-Trie Approach
         *
         * TC: O(32)
         * SC: O(1)
         */
        private void insert(int num) {
            BitTrieNode crawler = root;
            for (int i = 31; i >= 0; i--) { // TC: O(32)
                int ithBit = ((num >> i) & 1);
                if (ithBit == 0) {
                    // move to left child
                    if (crawler.left == null) {
                        crawler.left = new BitTrieNode();
                    }
                    crawler = crawler.left;
                } else {
                    // move to left child
                    if (crawler.right == null) {
                        crawler.right = new BitTrieNode();
                    }
                    crawler = crawler.right;
                }
            }
        }

        /**
         * Using Bit-Trie Approach
         *
         * TC: O(32)
         * SC: O(1)
         */
        private int getMaxXOR(int num) {
            int maxXOR = 0;
            BitTrieNode crawler = root;
            for (int i = 31; i >= 0; i--) { // TC: O(32)
                int ithBit = ((num >> i) & 1);
                if (crawler == null) {
                    break;
                }
                if (ithBit == 0) {
                    // we will try to XOR with 1 i.e. right child
                    if (crawler.right != null) {
                        maxXOR += (1 << i);
                        crawler = crawler.right;
                    } else {
                        crawler = crawler.left;
                    }
                } else {
                    // we will try to XOR with 0 i.e. left child
                    if (crawler.left != null) {
                        maxXOR += (1 << i);
                        crawler = crawler.left;
                    } else {
                        crawler = crawler.right;
                    }
                }
            }
            return maxXOR;
        }

        class BitTrieNode {
            BitTrieNode left;
            BitTrieNode right;
        }
    }
}
