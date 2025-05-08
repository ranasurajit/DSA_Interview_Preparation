package Recursion.P16_Josephus_Problem;

import java.util.ArrayList;
import java.util.List;

public class Josephus_Problem {
    public static void main(String[] args) {
        Josephus_Problem solution = new Josephus_Problem();

        int n = 5;
        int k = 3;
        int safePerson = solution.safePos(n, k);
        System.out.println(safePerson);
    }

    /**
     * Approach : Using Recursion Approach
     * 
     * TC: O(N ^ 2 + N) ~ O(N ^ 2)
     * SC: O(2 x N) ~ O(N)
     */
    private int safePos(int n, int k) {
        List<Integer> persons = new ArrayList<Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            persons.add(i + 1);
        }
        k = k - 1; // 0 based indexing
        solveRecursion(0, persons, k); // TC: O(N ^ 2), SC: O(N)
        return persons.get(0);
    }

    /**
     * TC: O(N ^ 2)
     * SC: O(N)
     */
    private void solveRecursion(int startIndex, List<Integer> persons, int k) {
        // Base Case
        if (persons.size() == 1) {
            return;
        }
        // Hypothesis
        /**
         * We assume that if we eliminate the kth person from startIndex, then recussion
         * will handle eliminating other persons with kth offset from startIndex
         */
        int killIndex = (startIndex + k) % persons.size();
        persons.remove(killIndex); // TC: O(N)
        // now the next person to start the count will be from killIndex
        solveRecursion(killIndex, persons, k);
        // Induction - Not required
    }
}
