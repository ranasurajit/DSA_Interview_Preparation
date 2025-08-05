package Math_Combinatorics.Basic_Math.P8_Print_All_Divisors_Of_A_Number;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Print_All_Divisors_Of_A_Number {
    public static void main(String[] args) {
        Print_All_Divisors_Of_A_Number solution = new Print_All_Divisors_Of_A_Number();

        int n1 = 10;
        List<Integer> divisors1 = solution.printDivisors(n1);
        System.out.println(divisors1);

        int n2 = 100;
        List<Integer> divisors2 = solution.printDivisors(n2);
        System.out.println(divisors2);
    }

    /**
     * Approach : Using Optimal (Math + Simulation) Approach
     * 
     * TC: O(Sqrt(N)) + O(Sqrt(N)) ~ O(Sqrt(N))
     * SC: O(1)
     */
    public List<Integer> printDivisors(int n) {
        List<Integer> divs = new ArrayList<Integer>();
        Stack<Integer> st = new Stack<Integer>();
        for (int i = 1; i * i <= n; i++) { // TC: O(Sqrt(N))
            if (n % i == 0) {
                divs.add(i);
                if ((n / i) != i) {
                    st.push(n / i);
                }
            }
        }
        while (!st.isEmpty()) { // TC: O(Sqrt(N))
            divs.add(st.pop());
        }
        return divs;
    }
}
