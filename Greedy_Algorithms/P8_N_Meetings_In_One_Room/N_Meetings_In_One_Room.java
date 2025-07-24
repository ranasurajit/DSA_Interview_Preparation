package Greedy_Algorithms.P8_N_Meetings_In_One_Room;

import java.util.Arrays;

public class N_Meetings_In_One_Room {
    public static void main(String[] args) {
        N_Meetings_In_One_Room solution = new N_Meetings_In_One_Room();

        int[] start = { 1, 3, 0, 5, 8, 5 };
        int[] end = { 2, 4, 6, 7, 9, 9 };

        int maxMeetings = solution.maxMeetings(start, end);
        System.out.println(maxMeetings);
    }

    /**
     * Approach : Using Greedy Approach
     * 
     * TC: O(N) + O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(N)
     */
    public int maxMeetings(int start[], int end[]) {
        int n = start.length;
        Pair[] meetings = new Pair[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            meetings[i] = new Pair(start[i], end[i]);
        }
        /**
         * To maximize the number of meetings we need to select the
         * meetings which has early end times and non-overlapping
         * 
         * so, we will sort 'meetings' in increasing order of end time
         */
        Arrays.sort(meetings, (a, b) -> a.end - b.end); // TC: O(N x log(N))
        int count = 1; // as we will take the 1st meeting
        int freeTime = meetings[0].end;
        for (int i = 1; i < n; i++) { // TC: O(N)
            if (meetings[i].start > freeTime) {
                count++;
                freeTime = meetings[i].end;
            }
        }
        return count;
    }

    private class Pair {
        int start;
        int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
