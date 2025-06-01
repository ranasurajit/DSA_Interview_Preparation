package Line_Sweep.P7_Corporate_Flight_Bookings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Corporate_Flight_Bookings {
    public static void main(String[] args) {
        Corporate_Flight_Bookings solution = new Corporate_Flight_Bookings();

        int[][] bookings = { { 1, 2, 10 }, { 2, 3, 20 }, { 2, 5, 25 } };
        int n = 5;

        int[] totalSeats = solution.corpFlightBookings(bookings, n);
        System.out.println(Arrays.toString(totalSeats));
    }

    /**
     * Approach : Using Line Sweep + Binary Search Approach
     *
     * TC: O(M x log(M) + N x log(M)) ~ O((M + N )x log(M))
     * SC: O(2 x M) ~ O(M)
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        TreeMap<Integer, Integer> flightMap = new TreeMap<Integer, Integer>(); // SC: O(2 x M) ~ O(M)
        for (int[] booking : bookings) { // TC: O(M)
            int first = booking[0];
            int last = booking[1];
            int seats = booking[2];
            // both [first, last] are inclusives
            flightMap.put(first, flightMap.getOrDefault(first, 0) + seats); // TC: O(log(M))
            flightMap.put(last + 1, flightMap.getOrDefault(last + 1, 0) - seats); // TC: O(log(M))
        }
        int totalSeats = 0;
        for (Integer key : flightMap.keySet()) { // TC: O(2 x M)
            totalSeats += flightMap.get(key); // TC: O(log(M))
            flightMap.put(key, totalSeats); // TC: O(log(M))
        }
        List<Integer> keysList = new ArrayList<Integer>(flightMap.keySet()); // SC: O(2 x M) ~ O(M)
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) { // TC: O(N)
            answer[i] = getAnswerFromBinarySearch(i + 1, keysList, flightMap); // TC: O(log(M))
        }
        return answer;
    }

    /**
     * Find the lowerBound of target value in keysList so find keysList[i] >= target
     *
     * TC: O(log(2 x M)) ~ O(log(M))
     * SC: O(1)
     */
    private int getAnswerFromBinarySearch(int target, List<Integer> keysList,
            TreeMap<Integer, Integer> flightMap) {
        int low = 0;
        int high = keysList.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (keysList.get(mid) >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (low >= keysList.size()) {
            return 0;
        }
        int lb = keysList.get(low);
        if (lb != target) {
            if (low - 1 < 0) {
                return 0;
            }
            lb = keysList.get(low - 1);
        }
        return flightMap.get(lb);
    }
}
