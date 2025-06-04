package Stacks_Queues.Stacks.P12_The_Celebrity_Problem;

public class The_Celebrity_Problem {
    public static void main(String[] args) {
        The_Celebrity_Problem solution = new The_Celebrity_Problem();

        int[][] mat = { { 1, 1, 0 }, { 0, 1, 0 }, { 0, 1, 1 } };

        int celebrity1 = solution.celebrityUsingGraphs(mat);
        System.out.println(celebrity1);

        int celebrity2 = solution.celebrityUsingTwoPointers(mat);
        System.out.println(celebrity2);
    }

    /**
     * Approach II : Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(1)
     */
    public int celebrityUsingTwoPointers(int mat[][]) {
        int n = mat.length;
        int top = 0;
        int bottom = n - 1;

        while (top < bottom) { // TC: O(N)
            if (mat[top][bottom] == 1) {
                // index 'top' cannot be celebrity
                top++;
            } else if (mat[bottom][top] == 1) {
                // index 'bottom' cannot be celebrity
                bottom--;
            } else if (mat[top][bottom] == 0 && mat[bottom][top] == 0) {
                // both 'top' and 'bottom' indices cannot be celebrity
                top++;
                bottom--;
            }
        }
        if (top > bottom) {
            return -1;
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (i == top) {
                continue;
            }
            if (mat[top][i] == 0 && mat[i][top] == 1) {
                // do nothing as we have the celebrity as top or bottom
            } else {
                return -1;
            }
        }
        return top;
    }

    /**
     * Approach I : Using Graph (Indegrees and Outdegrees) Approach
     * 
     * TC: O(N ^ 2 + N) ~ O(N ^ 2)
     * SC: O(2 x N) ~ O(N)
     */
    public int celebrityUsingGraphs(int mat[][]) {
        int n = mat.length;
        int[] indegrees = new int[n]; // SC: O(N)
        int[] outdegrees = new int[n]; // TC: O(N)

        for (int i = 0; i < n; i++) { // TC: O(N)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (i != j && mat[i][j] == 1) {
                    indegrees[j]++;
                    outdegrees[i]++;
                }
            }
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (outdegrees[i] == 0 && indegrees[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
}
