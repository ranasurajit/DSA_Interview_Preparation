package Arrays.Utils;

import java.util.ArrayList;

public class ArrayUtils {
    public static ArrayList<Integer> convert1DArayToArrayList(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int val : arr) {
            list.add(val);
        }
        return list;
    }

    public static ArrayList<ArrayList<Integer>> convert2DArayToArrayList(int[][] matrix) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int[] row : matrix) {
            ArrayList<Integer> innerList = new ArrayList<>();
            for (int val : row) {
                innerList.add(val);
            }
            list.add(innerList);
        }
        return list;
    }
}
