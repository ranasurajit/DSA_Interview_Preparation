package Tries.P8_Count_Distinct_Substrings;

import java.util.HashSet;
import java.util.Set;

public class Count_Distinct_Substrings {
    public static void main(String[] args) {
        Count_Distinct_Substrings solution = new Count_Distinct_Substrings();

        String st1 = "ab";
        int countDistinctSubstrings1 = solution.countDistinctSubstringBruteForce(st1);
        System.out.println(countDistinctSubstrings1);

        String st2 = "ababa";
        int countDistinctSubstrings2 = solution.countDistinctSubstringUsingTrie(st2);
        System.out.println(countDistinctSubstrings2);
    }

    /**
     * Approach II : Using Trie Approach
     * TC: O(N x N) ~ O(N ^ 2)
     * SC: O(N x N) ~ O(N ^ 2)
     */
    public int countDistinctSubstringUsingTrie(String st) {
        int n = st.length();
        int[] count = { 0 };
        Trie trie = new Trie();
        for (int i = 0; i < n; i++) { // TC: O(N)
            TrieNode crawler = trie.getRoot();
            for (int j = i; j < n; j++) { // TC: O(N)
                crawler = trie.insert(crawler, st.charAt(j), count); // TC: O(1)
            }
        }
        // +1 is done for empty sub-string of String 'st'
        return count[0] + 1;
    }

    class Trie {
        TrieNode root = new TrieNode();

        private TrieNode getRoot() {
            return this.root;
        }

        /**
         * TC: O(26) ~ O(1)
         * SC: O(26) ~ O(1)
         */
        private TrieNode insert(TrieNode crawler, char ch, int[] count) {
            int idx = ch - 'a';
            if (crawler.children[idx] == null) {
                crawler.children[idx] = new TrieNode();
                count[0]++;
            }
            return crawler.children[idx];
        }
    }

    class TrieNode {
        TrieNode[] children;

        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    /**
     * Approach I : Using Brute-Force (Hashing + String Simulation) Approach
     * TC: O(N x N) ~ O(N ^ 2)
     * SC: O(N x N x L) ~ O(N ^ 3)
     * 
     * where L = average length of all distinct substrings ~ (N / 2)
     */
    public int countDistinctSubstringBruteForce(String st) {
        int n = st.length();
        Set<String> set = new HashSet<String>();
        set.add(""); // considering "" also a distinct sub-string of String 'st'
        for (int i = 0; i < n; i++) { // TC: O(N)
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < n; j++) { // TC: O(N)
                sb.append(st.charAt(j));
                set.add(sb.toString());
            }
        }
        return set.size();
    }
}
