package Graphs_Backup.Disjoint_Set_Union.P03_Satisfiability_Of_Equality_Equations;

import java.util.Arrays;

public class Satisfiability_Of_Equality_Equations {
    public static void main(String[] args) {
        Satisfiability_Of_Equality_Equations solution = new Satisfiability_Of_Equality_Equations();

        String[] equations = { "a==b", "b!=c", "c==a" };
        boolean isPossible = solution.equationsPossible(equations);
        System.out.println(isPossible);
    }

    /**
     * Using DSU Approach
     * 
     * TC: O(N ^ 2 + N) ~ O(N ^ 2)
     * SC: O(1)
     * 
     * @param equations
     * @return
     */
    public boolean equationsPossible(String[] equations) {
        int n = equations.length;
        int[] rank = new int[26]; // as all characters are of lowercase - SC: O(26)
        int[] parent = new int[26]; // as all characters are of lowercase - SC: O(26)
        // initialize 'parent' array
        for (int i = 0; i < 26; i++) { // TC: O(26)
            parent[i] = i;
        }
        // initialize 'rank' array
        Arrays.fill(rank, 1);

        // check first for all equalities equations
        for (int i = 0; i < n; i++) { // TC: O(N)
            int x = equations[i].charAt(0) - 'a';
            int y = equations[i].charAt(3) - 'a';
            // form union of all equations with '=='
            if (equations[i].charAt(1) == '=') {
                union(parent, rank, x, y); // TC: O(1)
            }
        }
        // validate for all inequalities equations
        for (int i = 0; i < n; i++) { // TC: O(N)
            int x = equations[i].charAt(0) - 'a';
            int y = equations[i].charAt(3) - 'a';
            // form union of all equations with '=='
            if (equations[i].charAt(1) == '!') {
                int xParent = find(parent, x); // TC: O(N)
                int yParent = find(parent, y); // TC: O(N)
                if (xParent == yParent) {
                    return false;
                }
            }
        }
        return true;
    }

    // Adding DSU approach (Union By Rank and Path Compression Approach)
    /**
     * DSU - Find Path by Compression Approach
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * @param parent
     * @param x
     * @return
     */
    private int find(int[] parent, int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent, parent[x]);
    }

    /**
     * DSU - Union By Rank Approach
     * 
     * TC: O(1)
     * SC: O(1)
     * 
     * @param parent
     * @param rank
     * @param x
     * @param y
     */
    private void union(int[] parent, int[] rank, int x, int y) {
        int xParent = find(parent, x);
        int yParent = find(parent, y);
        if (xParent == yParent) {
            // both x and y nodes belong to same set so do nothing
            return;
        }
        if (rank[x] > rank[y]) {
            // set x as parent of y
            parent[yParent] = xParent;
        } else if (rank[x] < rank[y]) {
            // set y as parent of x
            parent[xParent] = yParent;
        } else {
            // set either of them as parent and increment rank of parent
            parent[yParent] = xParent;
            rank[x]++;
        }
    }
}
