package Tries.P4_Replace_Words;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Replace_Words {
    private TrieNode root = new TrieNode();

    public static void main(String[] args) {
        Replace_Words solution = new Replace_Words();

        List<String> dictionary1 = Arrays.asList("cat", "bat", "rat");
        String sentence1 = "the cattle was rattled by the battery";
        String replacedSentence1 = solution.replaceWords(dictionary1, sentence1);
        System.out.println(replacedSentence1);

        List<String> dictionary2 = Arrays.asList("a", "b", "c");
        String sentence2 = "aadsfasf absbs bbab cadsfafs";
        String replacedSentence2 = solution.replaceWords(dictionary2, sentence2);
        System.out.println(replacedSentence2);
    }

    /**
     * Approach : Using Trie Approach
     *
     * TC: O(N x D) + O(W x D)
     * SC: O(26 x D)
     *
     * where
     * N = Number of root words in List 'dictionary'
     * D = Max(dictionary[i])
     * W = Number of words in String 'sentence'
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        Set<String> dict = new HashSet<String>(dictionary);
        for (String prefix : dictionary) { // TC: O(N)
            insert(prefix); // TC: O(D), SC: O(D)
        }
        String[] words = sentence.split(" "); // SC: O(W)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) { // TC: O(W)
            String root = getRoot(words[i]);
            if (dict.contains(root)) { // TC: O(D), SC: O(1)
                words[i] = root;
            }
            sb.append(words[i]).append(" ");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    /**
     * Using Trie Approach
     *
     * TC: O(D)
     * SC: O(26 x D)
     *
     * where D = Max(dictionary[i])
     */
    private void insert(String prefix) {
        TrieNode crawler = root;
        for (int i = 0; i < prefix.length(); i++) { // TC: O(D)
            int idx = prefix.charAt(i) - 'a';
            if (crawler.children[idx] == null) {
                crawler.children[idx] = new TrieNode();
            }
            crawler = crawler.children[idx];
        }
        crawler.isEnd = true;
    }

    /**
     * Using Trie Approach
     *
     * TC: O(D)
     * SC: O(1)
     *
     * where D = Max(dictionary[i])
     */
    private String getRoot(String s) {
        TrieNode crawler = root;
        int count = 0;
        for (int i = 0; i < s.length(); i++) { // TC: O(D)
            int idx = s.charAt(i) - 'a';
            if (crawler.children[idx] == null) {
                break;
            }
            crawler = crawler.children[idx];
            count++;
            if (crawler.isEnd) {
                return s.substring(0, count);
            }
        }
        return "";
    }

    class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[26];
            isEnd = false;
        }
    }
}
