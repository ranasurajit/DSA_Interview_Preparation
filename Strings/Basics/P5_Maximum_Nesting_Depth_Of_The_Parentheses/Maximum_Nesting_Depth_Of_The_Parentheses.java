package Strings.Basics.P5_Maximum_Nesting_Depth_Of_The_Parentheses;

public class Maximum_Nesting_Depth_Of_The_Parentheses {
    public static void main(String[] args) {
        Maximum_Nesting_Depth_Of_The_Parentheses solution = new Maximum_Nesting_Depth_Of_The_Parentheses();

        String s1 = "(1+(2*3)+((8)/4))+1";
        int maxDepth1 = solution.maxDepth(s1);
        System.out.println(maxDepth1);

        String s2 = "(1)+((2))+(((3)))";
        int maxDepth2 = solution.maxDepth(s2);
        System.out.println(maxDepth2);

        String s3 = "()(())((()()))";
        int maxDepth3 = solution.maxDepth(s3);
        System.out.println(maxDepth3);
    }

    /**
     * Approach : Using String Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int maxDepth(String s) {
        int n = s.length();
        int counter = 0;
        int i = 0;
        int maxDepth = 0;
        while (i < n) { // TC: O(N)
            char ch = s.charAt(i);
            if (ch == '(') {
                counter++;
            }
            maxDepth = Math.max(maxDepth, counter);
            if (ch == ')') {
                counter--;
            }
            i++;
        }
        return maxDepth;
    }
}
