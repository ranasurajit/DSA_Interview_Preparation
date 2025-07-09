package Graphs.Basics.P1_Graph_And_Vertices;

public class Graph_And_Vertices {
    public static void main(String[] args) {
        Graph_And_Vertices solution = new Graph_And_Vertices();

        int n = 5;
        long count = solution.count(n);
        System.out.println(count);
    }

    /**
     * Approach : Using Maths
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private long count(int n) {
        // for n vertices we can have 2 ^ (N x (N - 1)) / 2 undirected graphs
        long exp = ((long) n * (n - 1)) / 2;
        return (long) Math.pow(2, exp);
    }
}
