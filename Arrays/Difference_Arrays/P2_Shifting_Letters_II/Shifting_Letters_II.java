package Arrays.Difference_Arrays.P2_Shifting_Letters_II;

public class Shifting_Letters_II {
    public static void main(String[] args) {
        String s = "dztz";
        int[][] shifts = { { 0, 0, 0 }, { 1, 1, 1 } };
        String result = shiftingLetters(s, shifts);
        System.out.println(result);
    }

    /**
     * Using Difference Arrays Approach
     * 
     * TC: O(2 x N + Q) ~ O(N + Q)
     * SC: O(N)
     * 
     * @param s
     * @param shifts
     * @return
     */
    public static String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] alpha = new int[n];
        for (int[] range : shifts) { // TC: O(Q)
            int start = range[0];
            int end = range[1];
            int increment = range[2] == 0 ? -1 : 1;
            alpha[start] += increment;
            if (end + 1 < n) {
                alpha[end + 1] -= increment;
            }
        }
        for (int i = 1; i < n; i++) { // TC: O(N)
            alpha[i] = alpha[i - 1] + alpha[i];
        }
        StringBuilder sb = new StringBuilder(s); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            int shift = alpha[i] % 26;
            if (shift < 0) {
                shift += 26;
            }
            char newChar = (char) ('a' + (s.charAt(i) - 'a' + shift) % 26);
            sb.setCharAt(i, newChar);
        }
        return sb.toString();
    }
}
