package Two_Pointers.P6_Reverse_The_String;

import java.util.ArrayList;
import java.util.List;

public class Reverse_The_String {
    public static void main(String[] args) {
        String[] list = { "abcde", "coding", "hello1" };
        List<String> result = new ArrayList<String>();
        for (String str : list) {
            result.add(reverseString(str));
        }
        System.out.println(result);
    }

    /**
     * Using Two Pointers Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public static String reverseString(String str) {
        char[] ch = str.toCharArray();
        int n = ch.length;
        int start = 0;
        int end = n - 1;
        while (start < end) { // TC: O(N / 2)
            // swapping characters in indices start and end
            char temp = ch[end];
            ch[end] = ch[start];
            ch[start] = temp;
            // shrink the pointers
            start++;
            end--;
        }
        return String.valueOf(ch);
    }
}
