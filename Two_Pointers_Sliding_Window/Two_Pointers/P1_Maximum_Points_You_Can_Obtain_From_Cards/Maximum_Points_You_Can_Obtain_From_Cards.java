package Two_Pointers_Sliding_Window.Two_Pointers.P1_Maximum_Points_You_Can_Obtain_From_Cards;

public class Maximum_Points_You_Can_Obtain_From_Cards {
    public static void main(String[] args) {
        Maximum_Points_You_Can_Obtain_From_Cards solution = new Maximum_Points_You_Can_Obtain_From_Cards();

        int[] cardPoints = { 1, 2, 3, 4, 5, 6, 1 };
        int k = 3;

        int maximumScore = solution.maxScore(cardPoints, k);
        System.out.println(maximumScore);
    }

    /**
     * Approach : Using Two Pointers Approach
     *
     * TC: O(2 x K) ~ O(K)
     * SC: O(1)
     */
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int max = 0;
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < k; i++) { // TC: O(K)
            leftSum += cardPoints[i];
        }
        max = leftSum;
        int p = k - 1; // left pointer
        int q = n - 1; // right pointer
        while (p >= 0 && q >= 0 && k > 0) { // TC: O(K)
            leftSum -= cardPoints[p];
            rightSum += cardPoints[q];
            p--;
            q--;
            k--;
            max = Math.max(max, leftSum + rightSum);
        }
        return max;
    }
}
