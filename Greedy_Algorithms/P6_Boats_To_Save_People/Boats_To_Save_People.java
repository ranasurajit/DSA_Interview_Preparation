package Greedy_Algorithms.P6_Boats_To_Save_People;

import java.util.Arrays;

public class Boats_To_Save_People {
    public static void main(String[] args) {
        Boats_To_Save_People solution = new Boats_To_Save_People();

        int[] people1 = { 3, 2, 2, 1 };
        int limit1 = 3;
        int minimumBoats1 = solution.numRescueBoatsGreedyApproach(people1, limit1);
        System.out.println(minimumBoats1);

        int[] people2 = { 3, 5, 3, 4 };
        int limit2 = 5;
        int minimumBoats2 = solution.numRescueBoatsGreedyCleanApproach(people2, limit2);
        System.out.println(minimumBoats2);
    }

    /**
     * Approach II : Using Greedy (Cleaner Approach) Approach
     *
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    public int numRescueBoatsGreedyCleanApproach(int[] people, int limit) {
        int n = people.length;
        Arrays.sort(people); // TC: O(N x log(N))
        int p = 0;
        int q = n - 1;
        int ships = 0;
        while (p <= q) { // TC: O(N)
            if (people[p] + people[q] <= limit) {
                p++;
            }
            ships++;
            q--;
        }
        return ships;
    }

    /**
     * Approach I : Using Greedy Approach
     *
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    public int numRescueBoatsGreedyApproach(int[] people, int limit) {
        int n = people.length;
        Arrays.sort(people); // TC: O(N x log(N))
        int p = 0;
        int q = n - 1;
        int ships = 0;
        while (p <= q) { // TC: O(N)
            if (people[p] + people[q] <= limit) {
                ships++;
                p++;
                q--;
            } else if (people[q] <= limit) {
                ships++;
                q--;
            }
        }
        return ships;
    }
}
