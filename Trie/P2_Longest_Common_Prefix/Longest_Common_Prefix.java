package Trie.P2_Longest_Common_Prefix;

public class Longest_Common_Prefix {
    public static void main(String[] args) {
        String[] strs = { "flower", "flow", "flight" };
        Longest_Common_Prefix solution = new Longest_Common_Prefix();
        String commonPrefix = solution.longestCommonPrefix(strs);
        System.out.println(commonPrefix);
    }

    /**
     * TC: O(N + N ^ 2) ~ O(N ^ 2)
     * SC: O(N)
     */
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if (n == 1) {
            return strs[0];
        }
        Trie trie = new Trie();
        trie.insert(strs[0]); // TC: O(N), SC: O(N)
        int minLength = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) { // TC: O(N)
            minLength = Math.min(minLength, trie.searchCommon(strs[i])); // TC: O(N)
        }
        return strs[0].substring(0, minLength);
    }

    class Trie {

        TrieNode root;

        class TrieNode {
            boolean isEndOfWord;
            TrieNode[] children;

            public TrieNode() {
                isEndOfWord = false;
                children = new TrieNode[26];
            }
        }

        public Trie() {
            root = new TrieNode();
        }

        private TrieNode createNode() {
            return new TrieNode();
        }

        /**
         * TC: O(N)
         * SC: O(26 x N) ~ O(N)
         */
        public void insert(String word) {
            TrieNode crawler = root;
            int n = word.length();
            for (int i = 0; i < n; i++) { // TC: O(N)
                int idx = word.charAt(i) - 'a';
                if (crawler.children[idx] == null) {
                    crawler.children[idx] = createNode();
                }
                crawler = crawler.children[idx];
            }
            crawler.isEndOfWord = true;
        }

        /**
         * TC: O(N)
         * SC: O(1)
         */
        public int searchCommon(String word) {
            TrieNode crawler = root;
            int n = word.length();
            int count = 0;
            for (int i = 0; i < n; i++) { // TC: O(N)
                int idx = word.charAt(i) - 'a';
                if (crawler.children[idx] == null) {
                    return count;
                }
                crawler = crawler.children[idx];
                count++;
            }
            return count;
        }
    }
}
