package Tries.P2_Implement_Trie_ll;

import java.util.ArrayList;
import java.util.List;

public class Implement_Trie_ll {
    public static void main(String[] args) {
        String[] queries = { "Trie", "insert", "insert", "insert", "erase", "countWordsEqualTo",
                "countWordsStartingWith" };
        String[][] params = { {}, { "samsung" }, { "samsung" }, { "vivo" }, { "vivo" }, { "samsung" }, { "vi" } };

        List<Object> result = new ArrayList<Object>();
        Trie trie = null;
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            if (query.equals("Trie")) {
                trie = new Trie();
                result.add(null);
            } else if (query.equals("insert")) {
                trie.insert(params[i][0]);
                result.add(null);
            } else if (query.equals("erase")) {
                trie.erase(params[i][0]);
                result.add(null);
            } else if (query.equals("countWordsEqualTo")) {
                result.add(trie.countWordsEqualTo(params[i][0]));
            } else {
                result.add(trie.countWordsStartingWith(params[i][0]));
            }
        }
        System.out.println(result);
    }
}

/**
 * Approach : Using Trie Approach
 * 
 * TC: O(L x N)
 * SC: O(K x L)
 *
 * where
 * N = number of queries
 * L = Average(words) sent as parameter
 * K = distinct words insert into Trie
 */
class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * Using Trie Approach
     *
     * TC: O(L)
     * SC: O(L)
     */
    public void insert(String word) {
        TrieNode crawler = root;
        for (int i = 0; i < word.length(); i++) { // TC: O(L)
            int idx = word.charAt(i) - 'a';
            if (crawler.children[idx] == null) {
                crawler.children[idx] = new TrieNode();
            }
            crawler = crawler.children[idx];
            crawler.prefixCount++;
        }
        crawler.wordCount++;
    }

    /**
     * Using Trie Approach
     *
     * TC: O(L)
     * SC: O(1)
     */
    public int countWordsEqualTo(String word) {
        TrieNode crawler = root;
        for (int i = 0; i < word.length(); i++) { // TC: O(L)
            int idx = word.charAt(i) - 'a';
            if (crawler.children[idx] == null) {
                return 0;
            }
            crawler = crawler.children[idx];
        }
        return crawler.wordCount;
    }

    /**
     * Using Trie Approach
     *
     * TC: O(L)
     * SC: O(1)
     */
    public int countWordsStartingWith(String word) {
        TrieNode crawler = root;
        for (int i = 0; i < word.length(); i++) { // TC: O(L)
            int idx = word.charAt(i) - 'a';
            if (crawler.children[idx] == null) {
                return 0;
            }
            crawler = crawler.children[idx];
        }
        return crawler.prefixCount;
    }

    /**
     * Using Trie Approach
     *
     * TC: O(L)
     * SC: O(1)
     */
    public void erase(String word) {
        TrieNode crawler = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            crawler = crawler.children[idx];
            crawler.prefixCount--;
        }
        crawler.wordCount--;
    }

    class TrieNode {
        int wordCount;
        int prefixCount;
        TrieNode[] children;

        public TrieNode() {
            this.wordCount = 0;
            this.prefixCount = 0;
            this.children = new TrieNode[26];
        }
    }
}
