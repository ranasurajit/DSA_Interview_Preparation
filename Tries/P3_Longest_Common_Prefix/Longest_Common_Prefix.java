package Tries.P3_Longest_Common_Prefix;

public class Longest_Common_Prefix {
    public static void main(String[] args) {
        Longest_Common_Prefix solution = new Longest_Common_Prefix();

        String[] strs1 = { "flower", "flow", "flight" };
        String longestCommonPrefix1 = solution.longestCommonPrefix(strs1);
        System.out.println(longestCommonPrefix1);

        String[] strs2 = { "dog", "racecar", "car" };
        String longestCommonPrefix2 = solution.longestCommonPrefix(strs2);
        System.out.println(longestCommonPrefix2);
    }

    TrieNode root = new TrieNode();

    /**
     * Approach : Using Trie Approach
     *
     * TC: O(L) + O(N x L) ~ O(N x L)
     * SC: O(L)
     */
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if (n == 1) {
            return strs[0];
        }
        insert(strs[0]); // TC: O(L), SC: O(L)
        int minLength = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) { // TC: O(N)
            minLength = Math.min(minLength, searchPrefix(strs[i])); // TC: O(L), SC: O(1)
        }
        return strs[0].substring(0, minLength);
    }

    /**
     * Using Trie Approach
     *
     * TC: O(L)
     * SC: O(L)
     */
    private void insert(String word) {
        TrieNode crawler = root;
        for (int i = 0; i < word.length(); i++) { // TC: O(L)
            int idx = word.charAt(i) - 'a';
            if (crawler.children[idx] == null) {
                crawler.children[idx] = new TrieNode();
            }
            crawler = crawler.children[idx];
        }
    }

    /**
     * Using Trie Approach
     *
     * TC: O(L)
     * SC: O(1)
     */
    private int searchPrefix(String word) {
        TrieNode crawler = root;
        int count = 0;
        for (int i = 0; i < word.length(); i++) { // TC: O(L)
            int idx = word.charAt(i) - 'a';
            if (crawler.children[idx] == null) {
                return count;
            }
            crawler = crawler.children[idx];
            count++;
        }
        return count;
    }

    class TrieNode {
        TrieNode[] children;

        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }
}
