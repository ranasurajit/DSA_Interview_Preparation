package BitManipulation.Basics.P7_Swap_Two_Numbers;

import java.util.ArrayList;
import java.util.List;

public class Swap_Two_Numbers {
    public static void main(String[] args) {
        Swap_Two_Numbers solution = new Swap_Two_Numbers();

        int a1 = 13, b1 = 9;
        List<Integer> swappedNumbers1 = solution.get(a1, b1);
        System.out.println(swappedNumbers1);

        int a2 = 15, b2 = 8;
        List<Integer> swappedNumbers2 = solution.get(a2, b2);
        System.out.println(swappedNumbers2);
    }

    /**
     * Approach : Using Bit-Manipulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private List<Integer> get(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        List<Integer> result = new ArrayList<Integer>();
        result.add(a);
        result.add(b);
        return result;
    }
}
