package Tries.P9_Longest_Common_Suffix_Queries;

import java.util.Arrays;

public class Longest_Common_Suffix_Queries {
    public static void main(String[] args) {
        Longest_Common_Suffix_Queries solution = new Longest_Common_Suffix_Queries();
        String[] wordsContainer1 = { "abcd", "bcd", "xbcd" };
        String[] wordsQuery1 = { "cd", "bcd", "xyz" };
        int[] indices1 = solution.stringIndices(wordsContainer1, wordsQuery1);
        System.out.println(Arrays.toString(indices1));

        String[] wordsContainer2 = { "abcdefgh", "poiuygh", "ghghgh" };
        String[] wordsQuery2 = { "gh", "acbfgh", "acbfegh" };
        int[] indices2 = solution.stringIndices(wordsContainer2, wordsQuery2);
        System.out.println(Arrays.toString(indices2));
    }

    /**
     * Approach : Using Trie Approach
     *
     * TC: O(N x L) + O(Q x L) ~ O(L x (N + Q))
     * SC: O(N x 32) ~ O(N)
     */
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int n = wordsContainer.length;
        int q = wordsQuery.length;
        Trie trie = new Trie();
        TrieNode root = trie.getNode(0); // TC: O(1)

        for (int i = 0; i < n; i++) { // TC: O(N)
            if (wordsContainer[root.idx].length() > wordsContainer[i].length()) {
                root.idx = i;
            }
            trie.insert(root, i, wordsContainer); // TC: O(L), SC: O(1)
        }
        int[] result = new int[q];
        for (int i = 0; i < q; i++) { // TC: O(Q)
            result[i] = trie.search(root, wordsQuery[i]); // TC: O(L), SC: O(1)
        }
        return result;
    }

    class Trie {
        /**
         * Using Trie Approach
         *
         * TC: O(26) ~ O(1)
         * SC: O(1)
         */
        private TrieNode getNode(int idx) {
            TrieNode node = new TrieNode(idx);
            for (int i = 0; i < 26; i++) {
                node.children[i] = null;
            }
            return node;
        }

        /**
         * Using Trie Approach
         *
         * TC: O(L)
         * SC: O(1)
         */
        private void insert(TrieNode crawler, int idx, String[] words) {
            String word = words[idx];
            int n = word.length();
            for (int i = n - 1; i >= 0; i--) { // TC: O(L)
                int chIdx = word.charAt(i) - 'a';
                if (crawler.children[chIdx] == null) {
                    crawler.children[chIdx] = getNode(idx);
                }
                crawler = crawler.children[chIdx];
                if (words[crawler.idx].length() > n) {
                    crawler.idx = idx;
                }
            }
        }

        /**
         * Using Trie Approach
         *
         * TC: O(L)
         * SC: O(1)
         */
        private int search(TrieNode crawler, String word) {
            int resultIdx = crawler.idx;
            int n = word.length();
            for (int i = n - 1; i >= 0; i--) { // TC: O(L)
                int chIdx = word.charAt(i) - 'a';
                if (crawler.children[chIdx] == null) {
                    return resultIdx;
                }
                crawler = crawler.children[chIdx];
                resultIdx = crawler.idx;
            }
            return resultIdx;
        }
    }

    class TrieNode {
        int idx;
        TrieNode[] children;

        public TrieNode(int idx) {
            this.idx = idx;
            children = new TrieNode[26];
        }
    }
}
