package Greedy_Algorithms.P3_Lemonade_Change;

public class Lemonade_Change {
    public static void main(String[] args) {
        Lemonade_Change solution = new Lemonade_Change();

        int[] bills1 = { 5, 5, 5, 10, 20 };
        boolean canSell1 = solution.lemonadeChange(bills1);
        System.out.println(canSell1);

        int[] bills2 = { 5, 5, 10, 10, 20 };
        boolean canSell2 = solution.lemonadeChange(bills2);
        System.out.println(canSell2);
    }

    /**
     * Approach : Using Greedy Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public boolean lemonadeChange(int[] bills) {
        int fives = 0;
        int tens = 0;
        for (int bill : bills) { // TC: O(N)
            if (bill == 5) {
                // no change needed so possible
                fives++;
            } else if (bill == 10) {
                // atleast one 5's change is needed
                if (fives > 0) {
                    tens++;
                    fives--;
                } else {
                    // not possible to return change value
                    return false;
                }
            } else {
                // bill == 20
                if (tens > 0) {
                    // atleast one 10's change is needed
                    if (fives > 0) {
                        // atleast one 5's change is needed
                        tens--;
                        fives--;
                    } else {
                        // not possible to return change value
                        return false;
                    }
                } else if (fives >= 3) {
                    // atleast three 5's change is needed
                    fives -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
