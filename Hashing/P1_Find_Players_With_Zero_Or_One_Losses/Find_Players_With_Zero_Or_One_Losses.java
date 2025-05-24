package Hashing.P1_Find_Players_With_Zero_Or_One_Losses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Find_Players_With_Zero_Or_One_Losses {
    public static void main(String[] args) {
        Find_Players_With_Zero_Or_One_Losses solution = new Find_Players_With_Zero_Or_One_Losses();

        int[][] matches = { { 1, 3 }, { 2, 3 }, { 3, 6 }, { 5, 6 }, { 5, 7 }, { 4, 5 }, { 4, 8 }, { 4, 9 }, { 10, 4 },
                { 10, 9 } };

        List<List<Integer>> winnersBruteForce = solution.findWinnersBruteForce(matches);
        System.out.println(winnersBruteForce);

        List<List<Integer>> winnersOptimal = solution.findWinnersOptimal(matches);
        System.out.println(winnersOptimal);
    }

    /**
     * Approach II : Using Hashing Approach (Cleaner Approach)
     *
     * TC: O(N + K + K x log(K))
     * SC: O(4 x K) ~ O(K)
     *
     * where K = number of players who played atleast 1 match
     */
    public List<List<Integer>> findWinnersOptimal(int[][] matches) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Set<Integer> players = new HashSet<Integer>(); // SC: O(K)
        Map<Integer, Integer> lostPlayers = new HashMap<Integer, Integer>(); // SC: O(K)
        for (int[] match : matches) { // TC: O(N)
            players.add(match[0]);
            players.add(match[1]);
            lostPlayers.put(match[1], lostPlayers.getOrDefault(match[1], 0) + 1);
        }
        List<Integer> winners = new ArrayList<Integer>(); // SC: O(P)
        List<Integer> loosers = new ArrayList<Integer>(); // SC: O(K - P)
        for (Integer player : players) { // TC: O(K)
            if (!lostPlayers.containsKey(player)) {
                winners.add(player);
            } else {
                if (lostPlayers.get(player) == 1) {
                    loosers.add(player);
                }
            }
        }
        Collections.sort(winners); // TC: O(P x log(P))
        Collections.sort(loosers); // TC: O((K - P) x log(K - P))
        result.add(winners);
        result.add(loosers);
        return result;
    }

    /**
     * Approach I : Using Hashing Approach
     *
     * TC: O(N + K + K x log(K))
     * SC: O(2 x K) ~ O(K)
     *
     * where K = number of players who played atleast 1 match
     */
    public List<List<Integer>> findWinnersBruteForce(int[][] matches) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Map<Integer, int[]> map = new HashMap<Integer, int[]>(); // SC: O(K)
        for (int[] match : matches) { // TC: O(N)
            map.putIfAbsent(match[0], new int[] { 0, 0 });
            map.putIfAbsent(match[1], new int[] { 0, 0 });
            int[] winner = map.get(match[0]);
            winner[0] += 1;
            map.put(match[0], winner);
            int[] looser = map.get(match[1]);
            looser[1] += 1;
            map.put(match[1], looser);
        }
        List<Integer> winners = new ArrayList<Integer>(); // SC: O(P)
        List<Integer> loosers = new ArrayList<Integer>(); // SC: O(K - P)
        for (Integer key : map.keySet()) { // TC: O(K)
            int[] profile = map.get(key);
            if (profile[1] == 0) {
                winners.add(key); // TC: O(log(K)
            }
            if (profile[1] == 1) {
                loosers.add(key); // TC: O(log(K)
            }
        }
        Collections.sort(winners); // TC: O(P x log(P))
        Collections.sort(loosers); // TC: O((K - P) x log(K - P))
        result.add(winners);
        result.add(loosers);
        return result;
    }
}
