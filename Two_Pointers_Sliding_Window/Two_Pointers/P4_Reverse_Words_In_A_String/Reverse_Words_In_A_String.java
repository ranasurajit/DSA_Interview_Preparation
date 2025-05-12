package Two_Pointers_Sliding_Window.Two_Pointers.P4_Reverse_Words_In_A_String;

public class Reverse_Words_In_A_String {
    public static void main(String[] args) {
        Reverse_Words_In_A_String solution = new Reverse_Words_In_A_String();

        String s = "a good   example";
        String reversed = solution.reverseWords(s);
        System.out.println(reversed);
    }

    /**
     * Approach : Using Two Pointers Approach
     * 
     * TC: O(N)
     * SC: O(N)
     * 
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        char[] ch = s.toCharArray(); // SC: O(N)
        int n = ch.length;
        // reverse the entire string
        reverse(ch, 0, n - 1);
        int i = 0; // pointer to iterate the entire String
        // pointers to swap the words
        int left = 0;
        int right = 0;
        while (i < n) { // TC: O(N)
            while (i < n && ch[i] != ' ') {
                ch[right++] = ch[i++];
            }
            // at this point 'i' and 'right' pointers points to a space
            // reverse word from [left...right]
            if (left < right) {
                reverse(ch, left, right - 1);
                if (right < n) {
                    ch[right] = ' ';
                }
                right++;
                left = right;
            }
            i++;
        }
        return String.valueOf(ch).substring(0, right - 1);
    }

    /**
     * TC: O(N / 2) ~ O(N)
     * SC: O(1)
     * 
     * @param ch
     * @param a
     * @param b
     */
    private void reverse(char[] ch, int a, int b) {
        while (a < b) { // TC: O(N / 2)
            char temp = ch[b];
            ch[b] = ch[a];
            ch[a] = temp;
            a++;
            b--;
        }
    }
}
