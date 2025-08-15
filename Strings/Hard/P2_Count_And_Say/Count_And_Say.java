package Strings.Hard.P2_Count_And_Say;

public class Count_And_Say {
    public static void main(String[] args) {
        Count_And_Say solution = new Count_And_Say();

        int n1 = 10;
        String countAndSay1 = solution.countAndSay(n1);
        System.out.println(countAndSay1);

        int n2 = 4;
        String countAndSay2 = solution.countAndSay(n2);
        System.out.println(countAndSay2);
    }

    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(1.3 ^ N)
     * SC: O(N)
     */
    public String countAndSay(int n) {
        return solveRecursion(n);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(1.3 ^ N)
     * SC: O(N)
     */
    private String solveRecursion(int n) {
        // Base Case
        if (n == 1) {
            return "1";
        }
        // Recursion Calls
        String current = solveRecursion(n - 1);
        StringBuilder sb = new StringBuilder();
        int m = current.length();
        int idx = 0;
        while (idx < m) { // TC: O(N) in worst case
            int count = 1;
            while (idx < m - 1 && current.charAt(idx) == current.charAt(idx + 1)) {
                count++;
                idx++;
            }
            sb.append(count).append(current.charAt(idx));
            idx++;
        }
        return sb.toString();
    }
}
