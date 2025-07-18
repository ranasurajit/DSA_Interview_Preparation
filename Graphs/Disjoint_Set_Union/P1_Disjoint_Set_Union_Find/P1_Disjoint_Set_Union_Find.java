package Graphs.Disjoint_Set_Union.P1_Disjoint_Set_Union_Find;

import java.util.ArrayList;
import java.util.List;

public class P1_Disjoint_Set_Union_Find {
    public static void main(String[] args) {
        int n = 5;
        int k = 4;
        // quries[i][0] = find - 0, unionSet = 1
        int[][] queries = { { 0, 4 }, { 0, 1 }, { 1, 3, 1 }, { 0, 3 } };
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        DSU solution = new DSU();
        List<Object> result = new ArrayList<Object>();

        for (int i = 0; i < k; i++) {
            if (queries[i][0] == 0) {
                result.add(solution.find(parent, queries[i][1]));
            } else {
                solution.unionSet(parent, queries[i][1], queries[i][2]);
                result.add(null);
            }
        }
        System.out.println(result);
    }

    static class DSU {
        /**
         * Using DSU Approach - Find
         * 
         * TC: O(N)
         * SC: O(N)
         */
        int find(int par[], int x) {
            if (par[x] == x) {
                return x;
            }
            return find(par, par[x]);
        }

        /**
         * Using DSU Approach - Union
         * 
         * TC: O(N)
         * SC: O(N)
         */
        void unionSet(int par[], int x, int z) {
            int xParent = find(par, x); // TC: O(N), SC: O(N)
            int zParent = find(par, z); // TC: O(N), SC: O(N)
            if (xParent == zParent) {
                return;
            }
            par[xParent] = zParent;
        }
    }

}
