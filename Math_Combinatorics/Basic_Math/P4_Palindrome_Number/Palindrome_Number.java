package Math_Combinatorics.Basic_Math.P4_Palindrome_Number;

public class Palindrome_Number {
    public static void main(String[] args) {
        Palindrome_Number solution = new Palindrome_Number();

        int x1 = 121;
        boolean isPalindrome1 = solution.isPalindrome(x1);
        System.out.println(isPalindrome1);

        int x2 = -121;
        boolean isPalindrome2 = solution.isPalindrome(x2);
        System.out.println(isPalindrome2);

        int x3 = 10;
        boolean isPalindrome3 = solution.isPalindrome(x3);
        System.out.println(isPalindrome3);
    }

    /**
     * Approach : Using Math Approach
     * 
     * TC: O(log(N) Base 10)
     * SC: O(1)
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int num = x;
        int rev = 0;
        int rem = 0;
        while (x > 0) { // TC: O(log(N) Base 10)
            rem = x % 10;
            rev = rev * 10 + rem;
            x = x / 10;
        }
        return num == rev;
    }
}
