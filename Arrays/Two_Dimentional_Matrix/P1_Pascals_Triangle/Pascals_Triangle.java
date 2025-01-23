package Arrays.Two_Dimentional_Matrix.P1_Pascals_Triangle;

import java.util.ArrayList;

public class Pascals_Triangle {
    public static void main(String[] args) {
        int n = 5;
        ArrayList<ArrayList<Long>> triangle = printPascal(n);
        System.out.println(triangle);
    }

    /**
     * Using 2-D Matrix Property
     * 
     * TC: O(N ^ 2)
     * SC: O(1)
     * 
     * @param n
     * @return
     */
    public static ArrayList<ArrayList<Long>> printPascal(int n) {
        ArrayList<ArrayList<Long>> triangle = new ArrayList<ArrayList<Long>>();
        for (int i = 0; i < n; i++) { // TC: O(N)
            ArrayList<Long> row = new ArrayList<Long>();
            for (int j = 0; j <= i; j++) { // TC: O(N)
                if (j == 0 || j == i) {
                    row.add(1L);
                } else {
                    long x = triangle.get(i - 1).get(j - 1);
                    long y = triangle.get(i - 1).get(j);
                    row.add(x + y);
                }
            }
            triangle.add(row);
        }
        return triangle;
    }
}
