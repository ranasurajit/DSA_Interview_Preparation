package Recursion.P18_Tower_Of_Hanoi;

import java.util.ArrayList;

public class Tower_Of_Hanoi {
    public static void main(String[] args) {
        Tower_Of_Hanoi solution = new Tower_Of_Hanoi();

        int n = 4;
        ArrayList<ArrayList<Integer>> result = solution.towerOfHanoi(n);
        System.out.println(result);
    }

    /**
     * Approach : Using Recursion Approach
     * 
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    public ArrayList<ArrayList<Integer>> towerOfHanoi(int n) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int src = 1;
        int aux = 2;
        int des = 3;
        solveRecursion(n, src, des, aux, result);
        return result;
    }

    /**
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private void solveRecursion(int n, int src, int des, int aux,
            ArrayList<ArrayList<Integer>> result) {
        // Base Case
        if (n == 1) {
            ArrayList<Integer> current = new ArrayList<Integer>();
            current.add(src);
            current.add(des);
            result.add(new ArrayList<Integer>(current));
            return;
        }
        /**
         * Hypothesis
         * we will assume recursion will stack (n - 1) disks
         * if moved from source - 'src' to destination - 'aux'
         * using helper - 'des'
         */
        solveRecursion(n - 1, src, aux, des, result);
        // Induction - we will move nth disk from source 'src' to destination 'des'
        ArrayList<Integer> current = new ArrayList<Integer>();
        current.add(src);
        current.add(des);
        result.add(new ArrayList<Integer>(current));
        /**
         * Hypothesis
         * we will assume recursion will stack (n - 1) disks
         * if moved from source - 'aux' to destination - 'des'
         * using helper - 'src'
         */
        solveRecursion(n - 1, aux, des, src, result);
    }
}
