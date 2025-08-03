package Tries.P5_Find_The_Longest_String;

public class Find_The_Longest_String {
    TrieNode root = new TrieNode();

    public static void main(String[] args) {
        Find_The_Longest_String solution = new Find_The_Longest_String();

        String[] words1 = { "p", "pr", "pro", "probl", "problem", "pros", "process", "processor" };
        String longestStringAllPrefixInWords1 = solution.longestString(words1);
        System.out.println(longestStringAllPrefixInWords1);

        String[] words2 = { "ab", "a", "abc", "abd" };
        String longestStringAllPrefixInWords2 = solution.longestString(words2);
        System.out.println(longestStringAllPrefixInWords2);
    }

    /**
     * Approach : Using Trie Approach
     * 
     * TC: O(N x L) + O(N x L) ~ O(N x L)
     * SC: O(N x L)
     */
    public String longestString(String[] words) {
        for (String word : words) { // TC: O(N)
            insert(word); // TC: O(L)
        }
        String result = "";
        for (String word : words) { // TC: O(N)
            if (hasAllPrefixes(word) && (result.length() < word.length()
                    || (result.length() == word.length() && word.compareTo(result) < 0))) { // TC: O(L)
                /**
                 * checking for longest length to set in result,
                 * but if same length of word is found as result,
                 * then storing the lexicographically smallest one.
                 */
                result = word;
            }
        }
        return result;
    }

    /**
     * Using Trie Approach
     * 
     * TC: O(L)
     * SC: O(1)
     */
    private void insert(String word) {
        TrieNode crawler = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
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
     * TC: O(L)
     * SC: O(1)
     */
    private boolean hasAllPrefixes(String word) {
        TrieNode crawler = root;
        for (int i = 0; i < word.length(); i++) { // TC: O(L)
            int idx = word.charAt(i) - 'a';
            crawler = crawler.children[idx];
            if (crawler == null || !crawler.isEnd) {
                return false;
            }

        }
        return true;
    }

    class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.isEnd = false;
        }
    }
}
