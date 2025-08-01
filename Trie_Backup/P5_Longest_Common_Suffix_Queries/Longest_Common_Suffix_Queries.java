package Trie_Backup.P5_Longest_Common_Suffix_Queries;

import java.util.Arrays;

public class Longest_Common_Suffix_Queries {
    public static void main(String[] args) {
        Longest_Common_Suffix_Queries solution = new Longest_Common_Suffix_Queries();
        String[] wordsContainer = { "abcd", "bcd", "xbcd" };
        String[] wordsQuery = { "cd", "bcd", "xyz" };
        int[] indices = solution.stringIndices(wordsContainer, wordsQuery);
        System.out.println(Arrays.toString(indices));
    }

    /**
     * Using Tries Approach
     * 
     * TC: O(N x L + Q x L)
     * SC: O(1)
     * 
     * @param wordsContainer
     * @param wordsQuery
     * @return
     */
    private int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int n = wordsContainer.length;
        TrieNode root = getNode(0);
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (wordsContainer[root.idx].length() > wordsContainer[i].length()) {
                root.idx = i;
            }
            insert(root, wordsContainer, i); // TC: O(L), SC: O(1)
        }
        int q = wordsQuery.length;
        int[] indices = new int[q];
        for (int i = 0; i < q; i++) { // TC: O(Q)
            indices[i] = search(root, wordsQuery[i]); // TC: O(L), SC: O(1)
        }
        return indices;
    }

    class TrieNode {
        int idx;
        TrieNode[] children;

        public TrieNode() {
            idx = -1;
            children = new TrieNode[26];
        }
    }

    /**
     * TC: O(26) ~ O(1)
     * SC: O(1)
     * 
     * @param idx
     * @return
     */
    public TrieNode getNode(int idx) {
        TrieNode node = new TrieNode();
        node.idx = idx;
        for (int i = 0; i < 26; i++) {
            node.children[i] = null;
        }
        return node;
    }

    /**
     * TC: O(L)
     * SC: O(1)
     * 
     * @param crawler
     * @param wordsContainer
     * @param idx
     */
    public void insert(TrieNode crawler, String[] wordsContainer, int idx) {
        String word = wordsContainer[idx];
        int n = word.length();
        for (int i = n - 1; i >= 0; i--) {
            int chIdx = word.charAt(i) - 'a';
            if (crawler.children[chIdx] == null) {
                crawler.children[chIdx] = getNode(idx);
            }
            crawler = crawler.children[chIdx];
            if (wordsContainer[crawler.idx].length() > n) {
                crawler.idx = idx;
            }
        }
    }

    /**
     * TC: O(L)
     * SC: O(1)
     * 
     * @param crawler
     * @param word
     * @return
     */
    public int search(TrieNode crawler, String word) {
        int resultIdx = crawler.idx;
        int n = word.length();
        for (int i = n - 1; i >= 0; i--) {
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
