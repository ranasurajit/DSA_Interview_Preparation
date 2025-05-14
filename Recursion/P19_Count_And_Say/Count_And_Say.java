package Recursion.P19_Count_And_Say;

public class Count_And_Say {
    public static void main(String[] args) {
        Count_And_Say solution = new Count_And_Say();

        int n = 5;
        String result = solution.countAndSay(n);
        System.out.println(result);
    }

    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(2 ^ N + N) ~ O(2 ^ N)
     */
    public String countAndSay(int n) {
        // Base Case
        if (n == 1) {
            return "1";
        }
        // Hypothesis - we expect recursion to return result for smaller input (n - 1)
        String prev = countAndSay(n - 1);
        // Induction
        StringBuilder sb = new StringBuilder(); // SC: O(2 ^ N)
        int size = prev.length();
        for (int i = 0; i < size; i++) { // TC: O(2 ^ (N - 1))
            int count = 1;
            char ch = prev.charAt(i);
            while (i < size - 1 && prev.charAt(i) == prev.charAt(i + 1)) {
                count++;
                i++;
            }
            sb.append(count);
            sb.append(ch);
        }
        return sb.toString();
    }
}
