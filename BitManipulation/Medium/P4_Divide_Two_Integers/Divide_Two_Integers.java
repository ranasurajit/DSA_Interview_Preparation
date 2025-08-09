package BitManipulation.Medium.P4_Divide_Two_Integers;

public class Divide_Two_Integers {
    public static void main(String[] args) {
        Divide_Two_Integers solution = new Divide_Two_Integers();

        int dividend1 = 10, divisor1 = 3;
        int quotient1 = solution.divideBruteForce(dividend1, divisor1);
        System.out.println(quotient1);

        int dividend2 = 7, divisor2 = -3;
        int quotient2 = solution.divideOptimal(dividend2, divisor2);
        System.out.println(quotient2);
    }

    /**
     * Approach II : Using Math + Simulation (Optimal) Approach
     *
     * TC: O(log(D)) x O(log(D))
     * SC: O(1)
     *
     * where D = dividend (in worst case divisor = 1)
     */
    public int divideOptimal(int dividend, int divisor) {
        int neg = ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) ? -1 : 1;
        if (Math.abs(divisor) == 1) {
            long res = (long) dividend;
            res = neg * Math.abs(res);
            if (res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (res < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            return (int) res;
        }
        long dvd = dividend < 0 ? -1 * (long) dividend : (long) dividend;
        long dvs = divisor < 0 ? -1 * (long) divisor : (long) divisor;
        long result = 0L;
        while (dvd >= dvs) { // TC: O(log(D))
            int pow = 0;
            while (dvs * (long) Math.pow(2, pow + 1) <= dvd) { // TC: O(log(D))
                pow++;
            }
            result += (long) Math.pow(2, pow);
            dvd -= dvs * (long) Math.pow(2, pow);
        }
        result = result * neg;
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) result;
    }

    /**
     * Approach I : Using Math + Simulation Approach
     *
     * TC: O(D)
     * SC: O(1)
     *
     * where D = dividend (in worst case divisor = 1)
     */
    public int divideBruteForce(int dividend, int divisor) {
        int neg = ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) ? -1 : 1;
        if (Math.abs(divisor) == 1) {
            long res = (long) dividend;
            res = neg * Math.abs(res);
            if (res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (res < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            return (int) res;
        }
        long dvd = dividend < 0 ? -1 * (long) dividend : (long) dividend;
        long dvs = divisor < 0 ? -1 * (long) divisor : (long) divisor;
        long sum = 0L;
        long result = 0L;
        while (sum + dvs <= dvd) { // TC: O(D)
            result++;
            sum += dvs;
        }
        result = result * neg;
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) result;
    }
}
