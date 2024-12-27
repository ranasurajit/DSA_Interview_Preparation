package Graphs.Basics.P1_Graph_And_Vertices;

public class Graph_And_Vertices {
    public static void main(String[] args) {
        int n = 5;
        long count = countGraphs(n);
        System.out.println(count);
    }

    private static long countGraphs(int n) {
        int exp = (n * (n - 1)) / 2;
        return (long) Math.pow(2, exp);
    }
}
