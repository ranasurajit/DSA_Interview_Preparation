package Trie.P1_Implement_Trie_Prefix_Tree;

import java.util.ArrayList;
import java.util.List;

public class Implement_Trie_Prefix_Tree {
    public static void main(String[] args) {
        Trie trie = null;
        String[] operations = { "Trie", "insert", "search", "search", "startsWith", "insert", "search" };
        String[][] words = { {}, { "apple" }, { "apple" }, { "app" }, { "app" }, { "app" }, { "app" } };
        List<Object> result = new ArrayList<Object>();
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            if (operation.equals("Trie")) {
                trie = new Trie();
                result.add(null);
            } else if (operation.equals("insert")) {
                trie.insert(words[i][0]);
                result.add(null);
            } else if (operation.equals("search")) {
                result.add(trie.search(words[i][0]));
            } else if (operation.equals("startsWith")) {
                result.add(trie.startsWith(words[i][0]));
            }
        }
        System.out.println(result);
    }
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

    private TrieNode createNode() {
        return new TrieNode();
    }

    public Trie() {
        root = createNode();
    }

    /**
     * TC: O(N)
     * SC: O(N x 26) ~ O(N)
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
    public boolean search(String word) {
        TrieNode crawler = root;
        int n = word.length();
        int i = 0;
        for (i = 0; i < n; i++) { // TC: O(N)
            int idx = word.charAt(i) - 'a';
            if (crawler.children[idx] == null) {
                return false;
            }
            crawler = crawler.children[idx];
        }
        return i == n && crawler.isEndOfWord;
    }

    /**
     * TC: O(N)
     * SC: O(1)
     */
    public boolean startsWith(String prefix) {
        TrieNode crawler = root;
        int n = prefix.length();
        int i = 0;
        for (i = 0; i < n; i++) { // TC: O(N)
            int idx = prefix.charAt(i) - 'a';
            if (crawler.children[idx] == null) {
                return false;
            }
            crawler = crawler.children[idx];
        }
        return i == n;
    }
}
