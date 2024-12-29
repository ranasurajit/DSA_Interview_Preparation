package Two_Pointers.P1_Reverse_Vowels_Of_A_String;

public class Reverse_Vowels_Of_A_String {
    public static void main(String[] args) {
        String s = ".,";
        String result = reverseVowels(s);
        System.out.println(result);
    }

    /**
     * TC: O(N)
     * SC: O(N)
     */
    public static String reverseVowels(String s) {
        int n = s.length();
        int p = 0; // pointer at the beginning of String 's'
        int q = n - 1; // pointer at the end of String 's'
        char[] chars = s.toCharArray(); // SC: O(N)
        while (p < q) { // TC: O(N)
            while (p < n && !isVowel(chars[p])) {
                p++;
            }
            while (q > 0 && !isVowel(chars[q])) {
                q--;
            }
            if (p < q) {
                // swap the chars between index 'p' and 'q'
                char temp = chars[q];
                chars[q] = chars[p];
                chars[p] = temp;
                p++;
                q--;
            }
        }
        return String.valueOf(chars);
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
