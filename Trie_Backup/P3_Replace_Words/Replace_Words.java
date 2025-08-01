package Trie_Backup.P3_Replace_Words;

import java.util.Arrays;
import java.util.List;

public class Replace_Words {
    public static void main(String[] args) {
        Replace_Words solution = new Replace_Words();

        List<String> dictionary = Arrays.asList("cat", "bat", "rat");
        String sentence = "the cattle was rattled by the battery";
        String replacedSentence = solution.replaceWords(dictionary, sentence);

        System.out.println(replacedSentence);
    }

    /**
     * TC: O(N x L + M x L)
     * SC: O(N x L + M x L)
     * where
     * N = number of words in dictionary
     * L = average length of words
     * M = number of words in sentence
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (String rootWords : dictionary) { // TC: O(N)
            trie.insert(rootWords); // TC: O(L)
        }
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) { // TC: O(M)
            sb.append(trie.getReplacement(word)); // TC: O(L)
            sb.append(" ");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
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
            int n = word.length();
            TrieNode crawler = root;
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
        public String getReplacement(String word) {
            int n = word.length();
            TrieNode crawler = root;
            int count = 0;
            for (int i = 0; i < n; i++) { // TC: O(N)
                int idx = word.charAt(i) - 'a';
                if (crawler.children[idx] == null) {
                    return word;
                }
                crawler = crawler.children[idx];
                if (crawler.isEndOfWord) {
                    return word.substring(0, count + 1);
                }
                count++;
            }
            return word;
        }
    }
}
