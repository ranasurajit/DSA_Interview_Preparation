package Hashing.P10_Replace_Words;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Arrays.Utils.ArrayUtils;

public class Replace_Words {
    public static void main(String[] args) {
        Replace_Words solution = new Replace_Words();

        String[] dict = { "cat", "bat", "rat" };
        List<String> dictionary = ArrayUtils.convert1DStringArayToArrayList(dict);

        String sentence = "the cattle was rattled by the battery";

        String replacedSentence = solution.replaceWords(dictionary, sentence);
        System.out.println(replacedSentence);
    }

    /**
     * Approach : Using Hashing Approach
     *
     * TC: O(M + M x L) ~ O(M x L)
     * SC: O(M + N + 2 x L) ~ O(M + N + L)
     *
     * where M = Length(words in sentence), L = Length(sentence)
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        String[] words = sentence.split(" "); // TC: O(M), SC: O(M + L)
        Set<String> set = new HashSet<String>(dictionary); // SC: O(N)
        StringBuilder sb = new StringBuilder(); // SC: O(L)
        for (String word : words) { // TC: O(M)
            sb.append(replaceWithRoot(word, set)).append(" "); // TC: O(L)
        }
        return sb.toString().trim();
    }

    /**
     * TC: O(L)
     * SC: O(1)
     */
    private String replaceWithRoot(String word, Set<String> set) {
        for (int i = 1; i < word.length(); i++) {
            String segment = word.substring(0, i);
            if (set.contains(segment)) {
                return segment;
            }
        }
        return word;
    }
}
