package Greedy_Algorithms.P5_Bag_Of_Tokens;

import java.util.Arrays;

public class Bag_Of_Tokens {
    public static void main(String[] args) {
        Bag_Of_Tokens solution = new Bag_Of_Tokens();

        int[] tokens1 = { 100, 200, 300, 400 };
        int power1 = 200;
        int maxScore1 = solution.bagOfTokensScore(tokens1, power1);
        System.out.println(maxScore1);

        int[] tokens2 = { 200, 100 };
        int power2 = 150;
        int maxScore2 = solution.bagOfTokensScore(tokens2, power2);
        System.out.println(maxScore2);
    }

    /**
     * Approach : Using Greedy Approach
     *
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    public int bagOfTokensScore(int[] tokens, int power) {
        int n = tokens.length;
        Arrays.sort(tokens); // TC: O(N x log(N))
        /**
         * When we face-up we will increase score by 1 and choosing the smallest token
         * value
         * When score >= 1, we facedown and decrease score by 1 and choosing the
         * greatest token value
         */
        int p = 0; // start pointer of token value
        int q = n - 1; // end pointer of token value
        int currentScore = 0;
        int maxScore = 0;
        while (p <= q) { // TC: O(N)
            if (power >= tokens[p]) {
                // face-up
                currentScore++;
                power -= tokens[p];
                p++;
                maxScore = Math.max(maxScore, currentScore);
            } else if (currentScore >= 1) {
                // face-down
                currentScore -= 1;
                power += tokens[q];
                q--;
            } else {
                // no more possibility
                break;
            }
        }
        return maxScore;
    }
}
