package Two_Pointers_Sliding_Window.Two_Pointers.P2_Reverse_Vowels_In_String;

public class Reverse_Vowels_In_String {
    public static void main(String[] args) {
        Reverse_Vowels_In_String solution = new Reverse_Vowels_In_String();

        String s1 = "IceCreAm";
        String reverseResult1 = solution.reverseVowelsApproachI(s1);
        System.out.println(reverseResult1);

        String s2 = "leetcode";
        String reverseResult2 = solution.reverseVowelsApproachII(s2);
        System.out.println(reverseResult2);
    }

    /**
     * Approach II : Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public String reverseVowelsApproachII(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int p = 0;
        int q = n - 1;
        while (p < q) { // TC: O(N)
            if (!isVowel(chars[p])) {
                p++;
            } else if (!isVowel(chars[q])) {
                q--;
            } else {
                // swap
                char temp = chars[q];
                chars[q] = chars[p];
                chars[p] = temp;
                p++;
                q--;
            }
        }
        return String.valueOf(chars);
    }

    /**
     * Approach I : Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public String reverseVowelsApproachI(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int p = 0;
        int q = n - 1;
        while (p < q) { // TC: O(N)
            while (p < n && !isVowel(chars[p])) {
                p++;
            }
            while (q > 0 && !isVowel(chars[q])) {
                q--;
            }
            // at this point both the pointers, point to vowels
            // swap
            if (p < q) {
                char temp = chars[q];
                chars[q] = chars[p];
                chars[p] = temp;
            }
            p++;
            q--;
        }
        return String.valueOf(chars);
    }

    /**
     * TC: O(1)
     * SC: O(1)
     */
    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }
}
