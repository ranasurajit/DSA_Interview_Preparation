package Recursion.P1_Implement_Pow;

public class Implement_Pow {
    public static void main(String[] args) {
        Implement_Pow solution = new Implement_Pow();

        double b = -0.67000;
        int e = -7;

        double pow = solution.power(b, e);
        System.out.println(pow);
    }

    /**
     * Using Recursion
     * 
     * TC: O(log(E))
     * SC: O(log(E))
     * 
     * @param b
     * @param e
     * @return
     */
    double power(double b, int e) {
        if (e == 0) {
            return 1.0;
        }
        if (e < 0) {
            return 1 / power(b, -1 * e);
        }
        double half = power(b, e / 2);
        double answer = half * half;
        if ((e & 1) == 1) {
            // odd
            answer = b * answer;
        }
        return answer;
    }
}
