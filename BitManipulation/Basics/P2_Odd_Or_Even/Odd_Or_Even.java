package BitManipulation.Basics.P2_Odd_Or_Even;

public class Odd_Or_Even {
    public static void main(String[] args) {
        Odd_Or_Even solution = new Odd_Or_Even();

        int n1 = 15;
        boolean isEven1 = solution.isEven(n1);
        System.out.println(isEven1);

        int n2 = 44;
        boolean isEven2 = solution.isEven(n2);
        System.out.println(isEven2);
    }

    /**
     * Approach : Using Bit-Manipulation Approach
     * 
     * TC: O(1)
     * SC: O(1)
     */
    private boolean isEven(int n) {
        return (n & 1) == 0;
    }
}
