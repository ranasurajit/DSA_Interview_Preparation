package Graphs.Topological_Sort.P6_Alien_Dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Alien_Dictionary {
    public static void main(String[] args) {
        Alien_Dictionary solution = new Alien_Dictionary();

        String[] words1 = { "baa", "abcd", "abca", "cab", "cad" };
        String findOrder1 = solution.findOrder(words1);
        System.out.println(findOrder1);

        String[] words2 = { "ab", "cd", "ef", "ad" };
        String findOrder2 = solution.findOrder(words2);
        System.out.println(findOrder2);
    }

    /**
     * Approach : Using Topological Sorting + DFS Approach
     * 
     * TC: O(N x L) + O(N x L) + O(V + E) + O(V + E) ~ O(N x L) + O(V + E)
     * SC: O(V + E) + O(5 x 26) ~ O(V + E)
     */
    public String findOrder(String[] words) {
        /**
         * Here we nee dto figure out the Adjacency list based on the
         * order of words and based upon which character comes before
         * which character in each words
         */
        int n = words.length;
        boolean[] charsExist = new boolean[26]; // SC: O(26)
        for (int i = 0; i < n; i++) { // TC: O(N)
            for (char ch : words[i].toCharArray()) { // TC: O(L)
                charsExist[ch - 'a'] = true;
            }
        }
        Map<Integer, ArrayList<Integer>> adj = createGraph(words, n); // TC: O(V + E) + O(N x L)
        if (adj == null) {
            return "";
        }
        // Using DFS Approach
        /**
         * We cannot form a correct order if Graph has cycle
         * so we will do DFS to find out the same and also
         * do push nodes in Stack to form Topological Sorting
         */
        boolean[] visited = new boolean[26]; // SC: O(26)
        boolean[] inRecursion = new boolean[26]; // SC: O(26)
        boolean hasCycle = false;
        Stack<Integer> st = new Stack<Integer>(); // SC: O(26)
        for (int i = 0; i < 26; i++) {
            if (!visited[i] && charsExist[i] &&
                    dfsGraphTopoGraphCycle(i, visited, inRecursion, adj, st)) { // TC: O(V + E), SC: O(V)
                hasCycle = true;
            }
        }
        if (hasCycle) {
            return "";
        }
        StringBuilder sb = new StringBuilder(); // SC: O(26)
        while (!st.isEmpty()) {
            sb.append((char) ((int) 'a' + st.pop()));
        }
        return sb.toString();
    }

    /**
     * Using DFS Approach
     * 
     * TC: O(V + E)
     * SC: O(V)
     */
    private boolean dfsGraphTopoGraphCycle(int u, boolean[] visited, boolean[] inRecursion,
            Map<Integer, ArrayList<Integer>> adj, Stack<Integer> st) {
        visited[u] = true;
        inRecursion[u] = true;
        for (Integer v : adj.getOrDefault(u, new ArrayList<Integer>())) {
            if (!visited[v] && dfsGraphTopoGraphCycle(v, visited, inRecursion, adj, st)) {
                return true;
            } else if (inRecursion[v]) {
                return true;
            }
        }
        st.push(u);
        inRecursion[u] = false;
        return false;
    }

    /**
     * Using Hashing Approach
     * 
     * TC: O(V + E) + O(N x L)
     * SC: O(V + E)
     */
    private Map<Integer, ArrayList<Integer>> createGraph(String[] words, int n) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>(); // SC: O(V + E)
        for (int i = 1; i < n; i++) { // TC: O(N)
            String wordPrev = words[i - 1];
            String wordCurrent = words[i];
            if (wordPrev.startsWith(wordCurrent) &&
                    wordPrev.length() > wordCurrent.length()) {
                // Invalid input
                return null;
            }
            int m = Math.min(wordPrev.length(), wordCurrent.length());
            for (int j = 0; j < m; j++) { // TC: O(L)
                if (wordPrev.charAt(j) != wordCurrent.charAt(j)) {
                    adj.computeIfAbsent((wordPrev.charAt(j) - 'a'),
                            k -> new ArrayList<Integer>()).add(wordCurrent.charAt(j) - 'a');
                    break;
                }
            }
        }
        return adj;
    }
}
