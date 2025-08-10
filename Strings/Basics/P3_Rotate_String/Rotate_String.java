package Strings.Basics.P3_Rotate_String;

public class Rotate_String {
    public static void main(String[] args) {
        Rotate_String solution = new Rotate_String();

        String s1 = "abcde", goal1 = "cdeab";
        boolean isRotated1 = solution.rotateString(s1, goal1);
        System.out.println(isRotated1);

        String s2 = "abcde", goal2 = "abced";
        boolean isRotated2 = solution.rotateString(s2, goal2);
        System.out.println(isRotated2);
    }

    /**
     * Approach : Using Two Pointers Approach
     *
     * TC: O(2 x N) ~ O(N)
     * SC: O(1)
     */
    public boolean rotateString(String s, String goal) {
        int n = s.length();
        int m = goal.length();
        if (n != m) {
            return false;
        }
        s += s;
        int i = 0; // pointer at start of String 's'
        int j = 0; // pointer at start of String 'goal'
        // Applying Two Pointers
        while (i < 2 * n) { // TC: O(2 x N)
            while (i < 2 * n && j < n && s.charAt(i) == goal.charAt(j)) {
                i++;
                j++;
            }
            if (j == n) {
                // all characters matched
                return true;
            }
            // if both characters do not match, we reset j = 0
            i = i - j; // going back to previous index of index 'i'
            i++;
            j = 0;
        }
        return false;
    }
}
